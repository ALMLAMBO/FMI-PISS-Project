package com.rateuni.backend.models.request_response.request;

public class UserInfoRequest {
    private int userId;

    private int degreeId;

    public UserInfoRequest(int userId, int degreeId) {
        this.userId = userId;
        this.degreeId = degreeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }
}