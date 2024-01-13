package com.rateuni.backend.models.link_models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

public class ReviewDiscipline implements Serializable {
    private int reviewId;

    private int disciplineId;

    public ReviewDiscipline() {
    }

    public ReviewDiscipline(int reviewId, int disciplineId) {
        this.reviewId = reviewId;
        this.disciplineId = disciplineId;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReviewDiscipline that = (ReviewDiscipline) o;
        return reviewId == that.reviewId && disciplineId == that.disciplineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reviewId, disciplineId);
    }
}
