package com.rateuni.backend.models.request_response.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestProcess {
    private int reviewId;
    private int userId;
    private String status;
}
