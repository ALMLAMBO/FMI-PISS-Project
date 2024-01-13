package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Review;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews_requests")
public class ReviewRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @ManyToOne
    @JoinColumn (name = "review_id")
    private Review review;

    @Column(name = "status")
    private String Status;

    public ReviewRequest() {

    }

    public ReviewRequest(int requestId, Review review, String status) {
        this.requestId = requestId;
        this.review = review;
        Status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
