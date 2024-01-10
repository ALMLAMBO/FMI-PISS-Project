package com.rateuni.backend.firebase;

import com.rateuni.backend.models.base_models.UniRequest;
import com.rateuni.backend.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @PostMapping("/create-request")
    public void addRequst(@RequestBody UniRequest request) {
        requestService.saveRequest(1, request);
    }

    @GetMapping("/get-requests")
    public List<UniRequest> getAllRequests() throws ExecutionException, InterruptedException {
        return requestService.getAllRequests();
    }
}
