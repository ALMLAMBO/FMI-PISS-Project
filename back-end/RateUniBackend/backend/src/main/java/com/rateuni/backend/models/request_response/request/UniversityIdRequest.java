package com.rateuni.backend.models.request_response.request;

public class UniversityIdRequest {
    private int id;

    public UniversityIdRequest(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
