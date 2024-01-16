package com.rateuni.backend.models.link_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserReview implements Serializable {
    int userId;
    int reviewId;

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
