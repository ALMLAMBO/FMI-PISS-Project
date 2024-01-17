package com.rateuni.backend.controllers;

import com.rateuni.backend.models.link_models.ReviewRequest;
import com.rateuni.backend.models.link_models.UserRequest;
import com.rateuni.backend.models.request_response.request.ReviewRequestProcess;
import com.rateuni.backend.models.request_response.request.UserRequestProcess;
import com.rateuni.backend.services.business_logic.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin
public class AdminController {
    private final RequestService requestService;

    @GetMapping("/get-user-requests")
    public ResponseEntity<List<UserRequest>> getUserRequests() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(requestService.getAllUsersRequests());
    }

    @GetMapping("/get-review-requests")
    public ResponseEntity<List<ReviewRequest>> getReviewRequest() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(requestService.getAllReviewRequests());
    }

    @PostMapping("/process-user-requests")
    public ResponseEntity<String> processUserRequests(@RequestBody List<UserRequestProcess> userRequestProcesses) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(requestService.processUserRequests(userRequestProcesses));
    }

    @PostMapping("/process-review-requests")
    public ResponseEntity<String> processReviewRequests(@RequestBody List<ReviewRequestProcess> reviewRequestProcesses) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(requestService.processReviewRequests(reviewRequestProcesses));
    }
}
