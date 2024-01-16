package com.rateuni.backend.models.link_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private int requestId;
    private String username;
    private String university;
    private String faculty;
    private String degree;
    private String facultyNumber;
    private String status;
    private boolean approved;
}
