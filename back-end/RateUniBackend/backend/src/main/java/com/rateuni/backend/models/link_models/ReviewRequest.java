package com.rateuni.backend.models.link_models;

public class ReviewRequest {
    private int requestId;

    private int reviewId;

    private String username;

    private String degree;

    private String discipline;

    private String status;

    public ReviewRequest() {

    }

    public ReviewRequest(int requestId, int reviewId, String username,
                         String degree, String discipline, String status) {

        this.requestId = requestId;
        this.reviewId = reviewId;
        this.username = username;
        this.degree = degree;
        this.discipline = discipline;
        this.status = status;
    }


}
