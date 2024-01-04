package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.*;
import com.rateuni.backend.models.link_models.*;
import com.rateuni.backend.repositories.base_repos.DegreeRepository;
import com.rateuni.backend.repositories.base_repos.FacultyRepository;
import com.rateuni.backend.repositories.base_repos.UniUserRepository;
import com.rateuni.backend.repositories.base_repos.UniversityRepository;
import com.rateuni.backend.repositories.link_repos.*;
import jakarta.transaction.Transactional;
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
            Triplet<University, Faculty, Degree> uniData = checkData(universityId, facultyId, degreeId);
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

    private Triplet<University, Faculty, Degree> checkData(int universityId, int facultyId, int degreeId) {
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
}
