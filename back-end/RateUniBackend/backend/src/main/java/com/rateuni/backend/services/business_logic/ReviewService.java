package com.rateuni.backend.services.business_logic;

import com.google.cloud.firestore.Query;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.link_models.ReviewDiscipline;
import com.rateuni.backend.models.link_models.UserReview;
import com.rateuni.backend.models.request_response.request.CreateReviewRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewService extends BaseService {
    private DisciplineService disciplineService;

    public ReviewService() {
        disciplineService = new DisciplineService();
    }

    public List<Review> getAllReviews() throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                .orderBy("publishedAt", Query.Direction.DESCENDING)
                .limit(6)
                .get()
                .get()
                .toObjects(Review.class);
    }

    public List<Review> getAllReviewsForDiscipline(int disciplineId) throws ExecutionException, InterruptedException {
        List<Review> reviews = new ArrayList<>();

        List<ReviewDiscipline> reviewDisciplines = firestore
                .collection(CollectionsNames.REVIEWS_DISCIPLINES_COLLECTION_NAME)
                .whereEqualTo("disciplineId", disciplineId)
                .get()
                .get()
                .toObjects(ReviewDiscipline.class);

        for (ReviewDiscipline reviewDiscipline : reviewDisciplines) {
            Review review = getReview(reviewDiscipline.getReviewId());

            reviews.add(review);
        }

        return reviews;
    }

    public Review getReview(int reviewId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                .whereEqualTo("id", reviewId)
                .get()
                .get()
                .toObjects(Review.class)
                .get(0);
    }

    public String createReview(CreateReviewRequest request) {
        try {
            long prevId = getId(CollectionsNames.REVIEWS_COLLECTION_NAME);
            request.getReview().setId((int) prevId);
            updateId(CollectionsNames.REVIEWS_COLLECTION_NAME);
            request.getReview().setVisible(false);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        request.getReview().setPublishedAt(new Date(System.currentTimeMillis()).toString());

        firestore
                .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                .add(request.getReview());

        firestore
                .collection(CollectionsNames.USERS_REVIEWS_COLLECTION_NAME)
                .add(new UserReview(request.getUserId(), request.getReview().getId()));

        firestore
                .collection(CollectionsNames.REVIEWS_DISCIPLINES_COLLECTION_NAME)
                .add(new ReviewDiscipline(request.getReview().getId(), request.getDisciplineId()));

        return "Review sent for approval";
    }
}
