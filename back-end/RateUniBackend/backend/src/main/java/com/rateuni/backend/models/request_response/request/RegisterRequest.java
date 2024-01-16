package com.rateuni.backend.models.request_response.request;

import com.rateuni.backend.models.base_models.UniUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private int universityId;
    private int facultyId;
    private int degreeId;
    private UniUser user;
}
