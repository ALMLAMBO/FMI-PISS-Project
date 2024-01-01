package com.rateuni.backend.models.base_models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "courseName")
    private String courseName;

    @Column(name = "description")
    private String description;

    @Column(name = "credits")
    private double credits;

    @Column(name = "type")
    private String type;

    @Column(name = "lecturer")
    private String lecturer;

    @Column(name = "assistants")
    private String assistants;

    public Course() {
    }

    public Course(int id, String courseName, String description,
                  double credits, String type, String lecturer, String assistants) {

        this.id = id;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        this.type = type;
        this.lecturer = lecturer;
        this.assistants = assistants;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getAssistants() {
        return assistants;
    }

    public void setAssistants(String assistants) {
        this.assistants = assistants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id
                && Double.compare(credits, course.credits) == 0
                && Objects.equals(courseName, course.courseName)
                && Objects.equals(description, course.description)
                && Objects.equals(type, course.type)
                && Objects.equals(lecturer, course.lecturer)
                && Objects.equals(assistants, course.assistants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, description, credits, type, lecturer, assistants);
    }
}
