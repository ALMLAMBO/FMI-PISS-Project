package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class UserReview implements Serializable {
    int userId;

    int reviewId;

    public UserReview() {

    }

    public UserReview(int userId, int reviewId) {
        this.userId = userId;
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReview that = (UserReview) o;
        return userId == that.userId && reviewId == that.reviewId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, reviewId);
    }
}
