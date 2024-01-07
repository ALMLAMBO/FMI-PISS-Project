package com.rateuni.backend.services;

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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private final UniversityRepository universityRepository;

    @Autowired
    private final FacultyRepository facultyRepository;

    @Autowired
    private final DegreeRepository degreeRepository;

    @Autowired
    private final DegreeDisciplineRepository degreeDisciplineRepository;

    @Autowired
    private final UserReviewRepository userReviewRepository;

    @Autowired
    private final UserRoleRepository userRoleRepository;

    @Autowired
    private final UserDisciplineRepository userDisciplineRepository;

    @Autowired
    private final UserDegreeRepository userDegreeRepository;

    @Autowired
    private final FacultyUserRepository facultyUserRepository;

    @Autowired
    private final UniUserRepository uniUserRepository;

    @Autowired
    private final UniversityUserRepository universityUserRepository;

    @Autowired
    private final DisciplineRepository disciplineRepository;



    @Autowired
    private final ReviewService reviewService;

    public UserService(UniversityRepository universityRepository,
                       FacultyRepository facultyRepository,
                       DegreeRepository degreeRepository,
                       DegreeDisciplineRepository degreeDisciplineRepository,
                       UserReviewRepository userReviewRepository,
                       UserRoleRepository userRoleRepository,
                       UserDisciplineRepository userDisciplineRepository,
                       UserDegreeRepository userDegreeRepository,
                       FacultyUserRepository facultyUserRepository,
                       UniUserRepository uniUserRepository,
                       UniversityUserRepository universityUserRepository,
                       DisciplineRepository disciplineRepository,
                       ReviewService reviewService) {

        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.degreeRepository = degreeRepository;
        this.degreeDisciplineRepository = degreeDisciplineRepository;
        this.userReviewRepository = userReviewRepository;
        this.userRoleRepository = userRoleRepository;
        this.userDisciplineRepository = userDisciplineRepository;
        this.userDegreeRepository = userDegreeRepository;
        this.facultyUserRepository = facultyUserRepository;
        this.uniUserRepository = uniUserRepository;
        this.universityUserRepository = universityUserRepository;
        this.disciplineRepository = disciplineRepository;
        this.reviewService = reviewService;
    }

    public List<Review> getAllReviewForUser(int userId) {
        return userReviewRepository
                .findAllById(List.of(userId))
                .stream()
                .map(UserReview::getReview)
                .collect(Collectors.toList());
    }

    public List<String> getRolesForUser(int userId) {
        return userRoleRepository
                .findAllById(List.of(userId))
                .stream()
                .map(x -> x.getRole().getRole())
                .collect(Collectors.toList());
    }

    public List<Discipline> getAllDisciplinesForUser(int userId) {
        return userDisciplineRepository
                .findAllById(List.of(userId))
                .stream()
                .map(UserDiscipline::getDiscipline)
                .collect(Collectors.toList());
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
