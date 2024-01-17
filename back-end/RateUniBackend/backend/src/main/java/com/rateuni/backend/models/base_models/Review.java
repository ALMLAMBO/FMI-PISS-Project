package com.rateuni.backend.models.base_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    private int id;
    private String comment;
    private String publishedAt;
    private String status;
    private double courseRating;
    private double lecturerRating;
    private double assistantsRating;
    private int difficulty;
    private int usefulness;
    private int workLoad;
    private boolean hasExam;
    private boolean hasProject;
    private boolean hasMidChecks;
    private boolean hasHomeworks;
    private boolean hasOnlineClasses;
    private boolean hasBooks;
    private boolean hasPresentations;
    private boolean hasAdditionalMaterials;
    private boolean visible;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id
                && Double.compare(courseRating, review.courseRating) == 0
                && Double.compare(lecturerRating, review.lecturerRating) == 0
                && Double.compare(assistantsRating, review.assistantsRating) == 0
                && difficulty == review.difficulty
                && usefulness == review.usefulness
                && workLoad == review.workLoad
                && hasExam == review.hasExam
                && hasProject == review.hasProject
                && hasMidChecks == review.hasMidChecks
                && hasHomeworks == review.hasHomeworks
                && hasOnlineClasses == review.hasOnlineClasses
                && hasBooks == review.hasBooks
                && hasPresentations == review.hasPresentations
                && hasAdditionalMaterials == review.hasAdditionalMaterials
                && Objects.equals(comment, review.comment)
                && Objects.equals(publishedAt, review.publishedAt)
                && Objects.equals(status, review.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, publishedAt, status, courseRating, lecturerRating,
                assistantsRating, difficulty, usefulness, workLoad, hasExam, hasProject,
                hasMidChecks, hasHomeworks, hasOnlineClasses, hasBooks,
                hasPresentations, hasAdditionalMaterials);
    }
}
