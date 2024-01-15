package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.link_models.ReviewDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ReviewService extends BaseService {
    @Autowired
    private DisciplineService disciplineService;

    public List<Review> getAllReviews() throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
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

    public void createReview(Review review) {
        try {
            long prevId = getId(CollectionsNames.REVIEWS_COLLECTION_NAME);
            review.setId((int) prevId);
            updateId(CollectionsNames.REVIEWS_COLLECTION_NAME);
            review.setVisible(false);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        review.setPublishedAt();

        firestore
                .collection(CollectionsNames.REVIEWS_COLLECTION_NAME)
                .add(review);
    }
}
