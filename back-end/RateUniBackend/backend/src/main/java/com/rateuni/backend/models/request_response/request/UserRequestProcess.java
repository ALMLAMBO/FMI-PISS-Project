package com.rateuni.backend.models.request_response.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestProcess {
    private int userId;
    private int requestId;
    private String status;
}
