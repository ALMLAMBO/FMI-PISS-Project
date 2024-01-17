package com.rateuni.backend.controllers;

import com.rateuni.backend.models.request_response.request.CreateReviewRequest;
import com.rateuni.backend.models.request_response.request.UserInfoRequest;
import com.rateuni.backend.models.request_response.response.UniUserInfo;
import com.rateuni.backend.services.business_logic.ReviewService;
import com.rateuni.backend.services.business_logic.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    @PostMapping("/get-user-info")
    public ResponseEntity<UniUserInfo> getUserInfo(@RequestBody UserInfoRequest userInfoRequest) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(userService.getUserInfo(userInfoRequest));
    }

    @PostMapping("/create-review")
    public ResponseEntity<String> createReview(@RequestBody CreateReviewRequest request) {
        return ResponseEntity.ok(reviewService.createReview(request));
    }
}
