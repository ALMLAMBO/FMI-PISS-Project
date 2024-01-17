package com.rateuni.backend.controllers;

import com.rateuni.backend.models.request_response.request.LoginRequest;
import com.rateuni.backend.models.request_response.request.RefreshTokenRequest;
import com.rateuni.backend.models.request_response.request.RegisterRequest;
import com.rateuni.backend.models.request_response.response.JwtAuthenticationResponse;
import com.rateuni.backend.models.request_response.response.JwtTokenResponse;
import com.rateuni.backend.services.authentication.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @GetMapping("/refresh")
    public ResponseEntity<JwtAuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest));
    }
}
