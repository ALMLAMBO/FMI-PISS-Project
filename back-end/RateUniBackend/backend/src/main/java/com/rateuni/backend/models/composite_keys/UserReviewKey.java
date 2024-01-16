package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserReviewKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "review_id")
    int reviewId;

    public UserReviewKey() {

    }

    public UserReviewKey(int userId, int reviewId) {
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
        UserReviewKey that = (UserReviewKey) o;
        return userId == that.userId && reviewId == that.reviewId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, reviewId);
    }
}
