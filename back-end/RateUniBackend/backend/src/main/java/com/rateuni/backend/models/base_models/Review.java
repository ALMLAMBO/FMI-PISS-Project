package com.rateuni.backend.models.base_models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "published_at")
    private Date publishedAt;

    @Column(name = "status")
    private String status;

    @Column(name = "course_rating")
    private double courseRating;

    @Column(name = "lecturer_rating")
    private double lecturerRating;

    @Column(name = "assistants_rating")
    private double assistantsRating;

    @Column(name = "difficulty")
    private int difficulty;

    @Column(name = "usefulness")
    private int usefulness;

    @Column(name = "work_load")
    private int workLoad;

    @Column(name = "exam")
    private boolean hasExam;

    @Column(name = "project")
    private boolean hasProject;

    @Column(name = "mid_checks")
    private boolean hasMidChecks;

    @Column(name = "homeworks")
    private boolean hasHomeworks;

    @Column(name = "online_classes")
    private boolean hasOnlineClasses;

    @Column(name = "books")
    private boolean hasBooks;

    @Column(name = "presentations")
    private boolean hasPresentations;

    @Column(name = "additional_materials")
    private boolean hasAdditionalMaterials;

    public Review() {
    }

    public Review(int id, String comment, Date publishedAt, String status,
                  double courseRating, double lecturerRating, double assistantsRating,
                  int difficulty, int usefulness, int workLoad, boolean hasExam,
                  boolean hasProject, boolean hasMidChecks, boolean hasHomeworks,
                  boolean hasOnlineClasses, boolean hasBooks, boolean hasPresentations,
                  boolean hasAdditionalMaterials) {

        this.id = id;
        this.comment = comment;
        this.publishedAt = publishedAt;
        this.status = status;
        this.courseRating = courseRating;
        this.lecturerRating = lecturerRating;
        this.assistantsRating = assistantsRating;
        this.difficulty = difficulty;
        this.usefulness = usefulness;
        this.workLoad = workLoad;
        this.hasExam = hasExam;
        this.hasProject = hasProject;
        this.hasMidChecks = hasMidChecks;
        this.hasHomeworks = hasHomeworks;
        this.hasOnlineClasses = hasOnlineClasses;
        this.hasBooks = hasBooks;
        this.hasPresentations = hasPresentations;
        this.hasAdditionalMaterials = hasAdditionalMaterials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCourseRating() {
        return courseRating;
    }

    public void setCourseRating(double courseRating) {
        this.courseRating = courseRating;
    }

    public double getLecturerRating() {
        return lecturerRating;
    }

    public void setLecturerRating(double lecturerRating) {
        this.lecturerRating = lecturerRating;
    }

    public double getAssistantsRating() {
        return assistantsRating;
    }

    public void setAssistantsRating(double assistantsRating) {
        this.assistantsRating = assistantsRating;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getUsefulness() {
        return usefulness;
    }

    public void setUsefulness(int usefulness) {
        this.usefulness = usefulness;
    }

    public int getWorkLoad() {
        return workLoad;
    }

    public void setWorkLoad(int workLoad) {
        this.workLoad = workLoad;
    }

    public boolean isHasExam() {
        return hasExam;
    }

    public void setHasExam(boolean hasExam) {
        this.hasExam = hasExam;
    }

    public boolean isHasProject() {
        return hasProject;
    }

    public void setHasProject(boolean hasProject) {
        this.hasProject = hasProject;
    }

    public boolean isHasMidChecks() {
        return hasMidChecks;
    }

    public void setHasMidChecks(boolean hasMidChecks) {
        this.hasMidChecks = hasMidChecks;
    }

    public boolean isHasHomeworks() {
        return hasHomeworks;
    }

    public void setHasHomeworks(boolean hasHomeworks) {
        this.hasHomeworks = hasHomeworks;
    }

    public boolean isHasOnlineClasses() {
        return hasOnlineClasses;
    }

    public void setHasOnlineClasses(boolean hasOnlineClasses) {
        this.hasOnlineClasses = hasOnlineClasses;
    }

    public boolean isHasBooks() {
        return hasBooks;
    }

    public void setHasBooks(boolean hasBooks) {
        this.hasBooks = hasBooks;
    }

    public boolean isHasPresentations() {
        return hasPresentations;
    }

    public void setHasPresentations(boolean hasPresentations) {
        this.hasPresentations = hasPresentations;
    }

    public boolean isHasAdditionalMaterials() {
        return hasAdditionalMaterials;
    }

    public void setHasAdditionalMaterials(boolean hasAdditionalMaterials) {
        this.hasAdditionalMaterials = hasAdditionalMaterials;
    }

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
