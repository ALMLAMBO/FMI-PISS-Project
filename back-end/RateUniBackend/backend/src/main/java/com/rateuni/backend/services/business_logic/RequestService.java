package com.rateuni.backend.services.business_logic;

import com.google.cloud.firestore.DocumentReference;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.link_models.ReviewRequest;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.models.request_response.request.ReviewRequestProcess;
import com.rateuni.backend.models.request_response.request.UserRequestProcess;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class RequestService extends BaseService {
    private UserService userService;

    public RequestService() {
        userService = new UserService();
    }

    public void saveUserRequest(UserRequest userRequest) {
        userRequest.setApproved(false);

        try {
            long prevId = getId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);
            userRequest.setRequestId((int) prevId);
            updateId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);
            userRequest.setStatus("pending");
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        firestore
                .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                .add(userRequest);
    }

    public void saveReviewRequest(ReviewRequest reviewRequest) {
        reviewRequest.setApproved(false);

        try {
            long prevId = getId(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME);
            reviewRequest.setRequestId((int) prevId);
            updateId(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME);
            reviewRequest.setStatus("pending");
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                .add(reviewRequest);
    }

    public String processUserRequests(List<UserRequestProcess> userRequestProcesses) throws ExecutionException, InterruptedException {
        for (UserRequestProcess userRequestProcess : userRequestProcesses) {
            UserRequest userRequest = firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("requestId", userRequestProcess.getRequestId())
                    .get()
                    .get()
                    .toObjects(UserRequest.class)
                    .get(0);

            DocumentReference userRequestDocumentReference = firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("requestId", userRequestProcess.getRequestId())
                    .get()
                    .get()
                    .getDocuments()
                    .getFirst()
                    .getReference();

            DocumentReference userDocumentReference = firestore
                    .collection(CollectionsNames.USERS_COLLECTION_NAME)
                    .whereEqualTo("id", userRequestProcess.getUserId())
                    .get()
                    .get()
                    .getDocuments()
                    .getFirst()
                    .getReference();

            if (Objects.equals(userRequestProcess.getStatus(), "потвърди")) {
                Map<String, Object> status = new HashMap<>();
                status.put("approved", true);
                userDocumentReference.set(status);
                userRequestDocumentReference.set(status);

                userService.createUniDataForUser(userRequestProcess.getUserId(),
                        userRequest.getUniversity(), userRequest.getFaculty(), userRequest.getDegree());
            }
            else if (Objects.equals(userRequestProcess.getStatus(), "отхвърли")) {
                Map<String, Object> status = new HashMap<>();
                status.put("approved", false);
                userDocumentReference.set(status);
                userRequestDocumentReference.set(status);
            }
        }

        return "Requests processed successfully";
    }

    public String processReviewRequests(List<ReviewRequestProcess> reviewRequestProcesses) throws ExecutionException, InterruptedException {
        for (ReviewRequestProcess reviewRequestProcess : reviewRequestProcesses) {
            ReviewRequest reviewRequest = firestore
                    .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("reviewId", reviewRequestProcess.getReviewId())
                    .get()
                    .get()
                    .toObjects(ReviewRequest.class)
                    .get(0);

            DocumentReference reviewRequestDocumentReference = firestore
                    .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("reviewId", reviewRequestProcess.getReviewId())
                    .get()
                    .get()
                    .getDocuments()
                    .getFirst()
                    .getReference();

            DocumentReference reivewDocumentReference = firestore
                    .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                    .whereEqualTo("id", reviewRequestProcess.getReviewId())
                    .get()
                    .get()
                    .getDocuments()
                    .getFirst()
                    .getReference();

            if (Objects.equals(reviewRequestProcess.getStatus(), "потвърди")) {
                Map<String, Object> status = new HashMap<>();
                status.put("approved", true);
                reviewRequestDocumentReference.set(status);

                status.remove("approved");
                status.put("visible", true);
                reivewDocumentReference.set(status);
            }
            else if (Objects.equals(reviewRequestProcess.getStatus(), "отхвърли")) {
                Map<String, Object> status = new HashMap<>();
                status.put("approved", false);
                reviewRequestDocumentReference.set(status);

                status.remove("approved");
                status.put("visible", false);
                reivewDocumentReference.set(status);
            }
        }

        return "Review requests processed successfully";
    }

    public List<UserRequest> getAllUsersRequests() throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                .whereEqualTo("approved", false)
                .whereEqualTo("status", "pending")
                .get()
                .get()
                .toObjects(UserRequest.class);
    }

    public List<ReviewRequest> getAllReviewRequests() throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                .whereEqualTo("approved", false)
                .whereEqualTo("status", "pending")
                .get()
                .get()
                .toObjects(ReviewRequest.class);
    }
}
