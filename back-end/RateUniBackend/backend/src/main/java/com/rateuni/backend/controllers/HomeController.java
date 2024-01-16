package com.rateuni.backend.controllers;

import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.services.business_logic.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    private final ReviewService reviewService;

    @GetMapping("/get-reviews")
    public ResponseEntity<List<Review>> getReviews() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }
}
