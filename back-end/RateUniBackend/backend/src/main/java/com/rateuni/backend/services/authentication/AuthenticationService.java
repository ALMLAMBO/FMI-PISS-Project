package com.rateuni.backend.services.authentication;

import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.request_response.request.LoginRequest;
import com.rateuni.backend.models.request_response.request.RefreshTokenRequest;
import com.rateuni.backend.models.request_response.request.RegisterRequest;
import com.rateuni.backend.models.request_response.response.JwtAuthenticationResponse;
import com.rateuni.backend.models.request_response.response.JwtTokenResponse;
import com.rateuni.backend.services.business_logic.UserService;
import com.rateuni.backend.services.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest registerRequest) {
        registerRequest.getUser()
                .setPassword(passwordEncoder.encode(
                        registerRequest.getUser().getPassword()));

        registerRequest.getUser().setRole("Student");

        userService.createUser(registerRequest.getUniversityId(),
                registerRequest.getFacultyId(), registerRequest.getDegreeId(), registerRequest.getUser());

        return "User created successfully";
    }

    public JwtTokenResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        try {
            UniUser user = (UniUser) userService.getUserByEmail(loginRequest.getEmail());
            if(!user.isApproved()) {
                return new JwtTokenResponse();
            }
            String jwtToken = jwtService.generateToken(user);
            String jwtRefreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);

            return new JwtTokenResponse(jwtToken, jwtRefreshToken, user.getId());
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String username = jwtService.extractUsername(refreshTokenRequest.getToken());
        try {
            UserDetails user = userService.getUserByUsername(username);
            if(jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
                String newJwtToken = jwtService.generateToken(user);

                return new JwtAuthenticationResponse(newJwtToken, refreshTokenRequest.getToken());
            }

            return null;
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
