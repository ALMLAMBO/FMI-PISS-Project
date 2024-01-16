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
public class ReviewDiscipline implements Serializable {
    private int reviewId;
    private int disciplineId;

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
