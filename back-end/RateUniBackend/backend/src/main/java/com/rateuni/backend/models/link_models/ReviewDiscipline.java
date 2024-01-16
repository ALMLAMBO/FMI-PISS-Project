package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.composite_keys.ReviewDisciplineKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reviews_disciplines")
public class ReviewDiscipline {
    @EmbeddedId
    private ReviewDisciplineKey id;

    @ManyToOne
    @MapsId("discipline_id")
    private Discipline discipline;

    @ManyToOne
    @MapsId("review_id")
    private Review review;

    public ReviewDiscipline() {
    }

    public ReviewDiscipline(Discipline discipline, Review review) {
        this.discipline = discipline;
        this.review = review;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
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
        ReviewDiscipline that = (ReviewDiscipline) o;
        return Objects.equals(id, that.id) && Objects.equals(discipline, that.discipline) && Objects.equals(review, that.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discipline, review);
    }
}
