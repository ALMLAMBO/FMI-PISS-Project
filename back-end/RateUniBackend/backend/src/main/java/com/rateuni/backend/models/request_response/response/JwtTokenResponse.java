package com.rateuni.backend.models.request_response.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenResponse {
    private String token;
    private String refreshToken;
    private int userId;
}
