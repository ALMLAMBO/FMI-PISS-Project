package com.rateuni.backend.models.request_response.request;

import com.rateuni.backend.models.base_models.Review;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private int userId;
    private int disciplineId;
    private Review review;
}
