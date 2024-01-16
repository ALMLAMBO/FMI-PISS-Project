package com.rateuni.backend.models.link_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private int requestId;
    private int reviewId;
    private String username;
    private String degree;
    private String discipline;
    private String status;
    private boolean approved;


}
