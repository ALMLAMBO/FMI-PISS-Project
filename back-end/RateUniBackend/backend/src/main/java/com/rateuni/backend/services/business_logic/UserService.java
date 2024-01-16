package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.base_models.*;
import com.rateuni.backend.models.link_models.UserDiscipline;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.models.link_models.UserReview;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService extends BaseService {
    private ReviewService reviewService;
    private UniversityService universityService;
    private FacultyService facultyService;
    private DegreeService degreeService;
    private DisciplineService disciplineService;

    public UserService() {
        universityService = new UniversityService();
        facultyService = new FacultyService();
        degreeService = new DegreeService();
        disciplineService = new DisciplineService();
        reviewService = new ReviewService();
    }

    public List<Review> getAllReviewForUser(int userId) throws ExecutionException, InterruptedException {
        List<Review> reviews = new ArrayList<>();

        List<UserReview> documents = firestore
                .collection(CollectionsNames.USERS_REVIEWS_COLLECTION_NAME)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .toObjects(UserReview.class);

        for (UserReview userReview : documents) {
            Review review = reviewService.getReview(userReview.getReviewId());

            reviews.add(review);
        }

        return reviews;
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
            Discipline discipline = disciplineService.getDiscipline(userDiscipline.getDisciplineId());

            disciplines.add(discipline);
        }

        return disciplines;
    }

    public UniUser getUser(int userId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.USERS_COLLECTION_NAME)
                .whereEqualTo("id", userId)
                .get()
                .get()
                .toObjects(UniUser.class)
                .get(0);
    }

    public UserDetails getUserByUsername(String username) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.USERS_COLLECTION_NAME)
                .whereEqualTo("username", username)
                .get()
                .get()
                .toObjects(UniUser.class)
                .get(0);
    }

    public UserDetails getUserByEmail(String email) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.USERS_COLLECTION_NAME)
                .whereEqualTo("email", email)
                .get()
                .get()
                .toObjects(UniUser.class)
                .get(0);
    }

    public UniUser createUser(int universityId, int facultyId, int degreeId, UniUser user) {
        University university;
        Faculty faculty;
        Degree degree;

        try {
            university = universityService.getUniversity(universityId);
            faculty = facultyService.getFaculty(facultyId);
            degree = degreeService.getDegree(degreeId);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            long prevId = getId(CollectionsNames.USERS_COLLECTION_NAME);
            user.setId((int) prevId);
            updateId(CollectionsNames.USERS_COLLECTION_NAME);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.USERS_COLLECTION_NAME)
                .add(user);

        UserRequest userRequest = new UserRequest();
        try {
            long requestPrevId = getId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);
            userRequest.setRequestId((int) requestPrevId);
            updateId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);

            userRequest.setUsername(user.getUsername());
            userRequest.setUniversity(university.getName());
            userRequest.setFaculty(faculty.getFacultyName());
            userRequest.setDegree(degree.getTitle());
            userRequest.setStatus("pending");
            userRequest.setApproved(false);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                .add(userRequest);

        return user;
    }

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                try {
                    UserDetails user1 = getUserByEmail(username);
                    if(user1 == null) {
                        return getUserByUsername(username);
                    }
                    return user1;
                }
                catch (ExecutionException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}