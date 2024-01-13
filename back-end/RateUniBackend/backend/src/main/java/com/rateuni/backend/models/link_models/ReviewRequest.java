package com.rateuni.backend.models.link_models;

public class ReviewRequest {
    private int requestId;

    private int reviewId;

    private String username;

    private String degree;

    private String discipline;

    private String status;

    private boolean approved;

    public ReviewRequest() {

    }

    public ReviewRequest(int requestId, int reviewId, String username,
                         String degree, String discipline, String status, boolean approved) {

        this.requestId = requestId;
        this.reviewId = reviewId;
        this.username = username;
        this.degree = degree;
        this.discipline = discipline;
        this.status = status;
        this.approved = approved;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
