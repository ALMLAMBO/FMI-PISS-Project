package com.rateuni.backend.models.request_response.request;

public class ReviewRequestProcess {
    private int reviewId;

    private int requestId;

    private String status;

    public ReviewRequestProcess() {

    }

    public ReviewRequestProcess(int reviewId, int requestId, String status) {
        this.reviewId = reviewId;
        this.requestId = requestId;
        this.status = status;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
