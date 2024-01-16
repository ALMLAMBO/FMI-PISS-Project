package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.link_models.ReviewRequest;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.models.request_response.request.ReviewRequestProcess;
import com.rateuni.backend.models.request_response.request.UserRequestProcess;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class RequestService extends BaseService {
    public void saveUserRequest(UserRequest userRequest) {
        userRequest.setApproved(false);

        firestore.runAsyncTransaction(x -> {
            try {
                long prevId = getId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);
                userRequest.setRequestId((int)prevId);
                updateId(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME);
                userRequest.setStatus("pending");
            } catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
            }

            return firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .add(userRequest);
        });
    }

    public void saveReviewRequest(ReviewRequest reviewRequest) {
        reviewRequest.setApproved(false);

        firestore.runAsyncTransaction(x -> {
            try {
                long prevId = getId(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME);
                reviewRequest.setRequestId((int)prevId);
                updateId(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME);
                reviewRequest.setStatus("pending");
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return firestore
                    .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                    .add(reviewRequest);
        });
    }

    public void processUserRequests(List<UserRequestProcess> userRequestProcesses) throws ExecutionException, InterruptedException {
        for (UserRequestProcess userRequestProcess : userRequestProcesses) {
            UserRequest userRequest = firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("id", userRequestProcess.getRequestId())
                    .whereEqualTo("userId", userRequestProcess.getUserId())
                    .get()
                    .get()
                    .toObjects(UserRequest.class)
                    .get(0);

            if (Objects.equals(userRequestProcess.getStatus(), "approved")) {
                userRequest.setApproved(true);
                userRequest.setStatus(userRequestProcess.getStatus());
            } else if (Objects.equals(userRequestProcess.getStatus(), "rejected")) {
                userRequest.setApproved(false);
                userRequest.setStatus(userRequestProcess.getStatus());
            }

            firestore.runAsyncTransaction(x -> firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .add(userRequest));
        }
    }

    public void processReviewRequests(List<ReviewRequestProcess> reviewRequestProcesses) throws ExecutionException, InterruptedException {
        for (ReviewRequestProcess reviewRequestProcess : reviewRequestProcesses) {
            ReviewRequest reviewRequest = firestore
                    .collection(CollectionsNames.REVIEWS_REQUESTS_COLLECTION_NAME)
                    .whereEqualTo("reviewId", reviewRequestProcess.getReviewId())
                    .get()
                    .get()
                    .toObjects(ReviewRequest.class)
                    .get(0);

            if (Objects.equals(reviewRequestProcess.getStatus(), "approved")) {
                reviewRequest.setApproved(true);
                reviewRequest.setStatus(reviewRequestProcess.getStatus());
            } else if (Objects.equals(reviewRequestProcess.getStatus(), "rejected")) {
                reviewRequest.setApproved(false);
                reviewRequest.setStatus(reviewRequestProcess.getStatus());
            }

            firestore.runAsyncTransaction(x -> firestore
                    .collection(CollectionsNames.USERS_REQUESTS_COLLECTION_NAME)
                    .add(reviewRequest));
        }
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

    private void addUniDataForUser() {

    }
}
