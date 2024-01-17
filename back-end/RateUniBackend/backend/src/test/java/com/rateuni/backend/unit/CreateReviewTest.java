package com.rateuni.backend.unit;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.rateuni.backend.BackendApplication;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.request_response.request.CreateReviewRequest;
import com.rateuni.backend.services.business_logic.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
public class CreateReviewTest {
    private ReviewService reviewService;
    private Review review;

    @BeforeAll
    public static void initFirebase() {
        InputStream serviceAccount = BackendApplication.class
                .getClassLoader()
                .getResourceAsStream("credentials.json");

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(FirebaseApp.getApps().isEmpty()) {
            FirebaseApp.initializeApp(options);
        }
    }

    @BeforeEach
    public void init() {
        reviewService = new ReviewService();
        review = new Review();
        review.setComment("test comment");
        review.setDifficulty(3);
        review.setCourseRating(4.2);
        review.setDifficulty(1);
        review.setHasBooks(false);
        review.setAssistantsRating(3.7);
        review.setUsefulness(5);
    }

    @Test
    public void testSuccessfulCreationOfReview() {
        CreateReviewRequest createReviewRequest = new CreateReviewRequest();
        createReviewRequest.setReview(review);
        createReviewRequest.setUserId(2);
        createReviewRequest.setDisciplineId(1);
        Assertions.assertEquals(reviewService.createReview(createReviewRequest), "Review sent for approval");
    }

    @Test
    public void testReviewCreationWithWrongUserId() {
        CreateReviewRequest createReviewRequest = new CreateReviewRequest();
        createReviewRequest.setReview(review);
        createReviewRequest.setUserId(20);
        createReviewRequest.setDisciplineId(1);
        Assertions.assertThrows(Exception.class, () -> reviewService.createReview(createReviewRequest));
    }

    @Test
    public void testReviewCreationWithWrongDisciplineId() {
        CreateReviewRequest createReviewRequest = new CreateReviewRequest();
        createReviewRequest.setReview(review);
        createReviewRequest.setUserId(2);
        createReviewRequest.setDisciplineId(20);
        Assertions.assertThrows(Exception.class, () -> reviewService.createReview(createReviewRequest));
    }
}
