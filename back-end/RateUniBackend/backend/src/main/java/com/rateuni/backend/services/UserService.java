package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.*;
import com.rateuni.backend.models.link_models.UserDiscipline;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.models.link_models.UserReview;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService extends BaseService {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private DegreeService degreeService;

    @Autowired
    private DisciplineService disciplineService;

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

    public void createUser(int universityId, int facultyId, int degreeId, UniUser user) {
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
    }
}