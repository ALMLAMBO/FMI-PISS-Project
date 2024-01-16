package com.rateuni.backend.models.request_response.response;

import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniUserInfo {
    private int userId;
    private String username;
    private String university;
    private String faculty;
    private String degree;
    private List<Discipline> disciplines;
    private List<Review> reviews;
}
