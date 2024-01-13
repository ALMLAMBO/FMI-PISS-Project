package com.rateuni.backend.services;

import com.google.cloud.firestore.DocumentReference;
import com.rateuni.backend.models.base_models.*;
import com.rateuni.backend.models.link_models.*;
import com.rateuni.backend.repositories.base_repos.*;
import com.rateuni.backend.repositories.link_repos.*;
import jakarta.transaction.Transactional;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.javatuples.Quintet;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService {
    public List<Review> getAllReviewForUser(int userId) throws ExecutionException, InterruptedException {
       List<Review> reviews = new ArrayList<>();

       List<UserReview> documents = firestore
               .collection(CollectionsNames.USERS_REVIEWS_COLLECTION_NAME)
               .whereEqualTo("userId", userId)
               .get()
               .get()
               .toObjects(UserReview.class);

        for (UserReview userReview : documents) {
            Review review = firestore
                    .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                    .whereEqualTo("id", userReview.getReviewId())
                    .get()
                    .get()
                    .toObjects(Review.class)
                    .get(0);

            reviews.add(review);
        }
       
       return reviews;
    }

    public List<String> getRolesForUser(int userId) throws ExecutionException, InterruptedException {
        List<String> roles = new ArrayList<>();

        List<UserRole> documents = firestore
                .collection(CollectionsNames.USERS_ROLES_COLLECTION_NAME)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .toObjects(UserRole.class);

        for (UserRole userRole : documents) {
            String role = firestore
                    .collection(CollectionsNames.ROLES_COLLECTION_NAME)
                    .whereEqualTo("id", userRole.getRoleId())
                    .get()
                    .get()
                    .toObjects(Role.class)
                    .get(0)
                    .getRole();

            roles.add(role);
        }

        return roles;
    }

    public List<Discipline> getAllDisciplinesForUser(int userId) throws ExecutionException, InterruptedException {
        List<Discipline> disciplines = new ArrayList<>();

        List<UserDiscipline> userDisciplines = firestore
                .collection(CollectionsNames.USERS_DISCIPLINES_COLLECTION_NAME)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .toObjects(UserDiscipline.class);

        for (UserDiscipline userDiscipline : userDisciplines) {
            Discipline discipline = firestore
                    .collection(CollectionsNames.DISCIPLINES_COLLECTION_NAME)
                    .whereEqualTo("id", userDiscipline.getDisciplineId())
                    .get()
                    .get()
                    .toObjects(Discipline.class)
                    .get(0);

            disciplines.add(discipline);
        }

        return disciplines;
    }

    @Transactional
    public void registerUser(int universityId, int facultyId, int degreeId, UniUser user) {
        try {
            Triplet<University, Faculty, Degree> uniData = checkUniData(universityId, facultyId, degreeId);
            Role role = new Role();
            role.setRole("student");

            uniUserRepository.save(user);
            universityUserRepository.save(new UniversityUser(uniData.getValue0(), user));
            facultyUserRepository.save(new FacultyUser(uniData.getValue1(), user));
            userDegreeRepository.save(new UserDegree(user, uniData.getValue2()));
            userRoleRepository.save(new UserRole(user, role));

            List<Discipline> requiredDisciplines = degreeDisciplineRepository
                    .findAllById(List.of(degreeId))
                    .stream()
                    .map(DegreeDiscipline::getDiscipline)
                    .toList();

            for (Discipline requiredDiscipline : requiredDisciplines) {
                userDisciplineRepository.save(new UserDiscipline(user, requiredDiscipline));
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void createReview(int universityId, int facultyId, int degreeId, int disciplineId, int userId, Review review) {
        try {
            UniUser user = checkUserData(universityId, facultyId, degreeId, disciplineId, userId);
            reviewService.createReview(disciplineId, review);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private Triplet<University, Faculty, Degree> checkUniData(int universityId, int facultyId, int degreeId) {
        University university = universityRepository
                .findById(universityId)
                .get();

        if(university == null) {
            throw new IllegalArgumentException("User Service register user: Invalid university id");
        }

        Faculty faculty = facultyRepository
                .findById(facultyId)
                .get();

        if(faculty == null) {
            throw new IllegalArgumentException("User Service register user: Invalid university id");
        }

        Degree degree = degreeRepository
                .findById(degreeId)
                .get();

        if(degree == null) {
            throw new IllegalArgumentException("User Service register user: Invalid degree id");
        }

        return new Triplet<>(university, faculty, degree);
    }

    private UniUser checkUserData(
            int universityId, int facultyId, int degreeId, int disciplineId, int userId) {

        Triplet<University, Faculty, Degree> uniData = checkUniData(universityId, facultyId, degreeId);
        Discipline discipline = disciplineRepository
                .findById(disciplineId)
                .get();

        if(discipline == null) {
            throw new IllegalArgumentException("User Service create review: Invalid discipline id");
        }

        UniUser user = uniUserRepository
                .findById(userId)
                .get();

        if(user == null) {
            throw new IllegalArgumentException("User Service create review: Invalid user id");
        }

        int expectedUniId = universityUserRepository
                .findById(userId)
                .get()
                .getUniversity()
                .getId();

        if(expectedUniId != universityId) {
            throw new IllegalArgumentException("User Service create review: User is not from this university");
        }

        int expectedFacultyId = facultyUserRepository
                .findById(userId)
                .get()
                .getFaculty()
                .getId();

        if(expectedFacultyId != facultyId) {
            throw new IllegalArgumentException("User Service create review: User is not from this faculty");
        }

        int expectedDegreeId = userDegreeRepository
                .findById(userId)
                .get()
                .getDegree()
                .getId();

        if(expectedDegreeId != degreeId) {
            throw new IllegalArgumentException("User Service create review: User is not from this degree");
        }

        int expectedDisciplineId = userDisciplineRepository
                .findAllById(List.of(userId))
                .stream()
                .filter(x -> x.getDiscipline().getId() == disciplineId)
                .map(x -> x.getDiscipline().getId())
                .toList()
                .get(0);

        if(expectedDisciplineId != disciplineId) {
            throw new IllegalArgumentException("User Service create review: User is not from this discipline");
        }

        return user;
    }
}
