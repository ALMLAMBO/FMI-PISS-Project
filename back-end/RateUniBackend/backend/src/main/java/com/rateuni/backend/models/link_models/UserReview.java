package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.composite_keys.UserReviewKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_reviews")
public class UserReview {
    @EmbeddedId
    private UserReviewKey id;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    @ManyToOne
    @MapsId("review_id")
    private Review review;

    public UserReview() {

    }

    public UserReview(UniUser user, Review review) {
        this.user = user;
        this.review = review;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserReview that = (UserReview) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, review);
    }
}
