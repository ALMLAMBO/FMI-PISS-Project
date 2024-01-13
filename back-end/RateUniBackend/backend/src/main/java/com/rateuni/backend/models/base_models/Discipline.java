package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.UserDiscipline;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

public class Discipline {
    private int id;

    private String courseName;

    private String description;

    private double credits;

    private String type;

    private String lecturer;

    private String assistants;

    public Discipline() {
    }

    public Discipline(int id, String courseName, String description,
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
        Discipline discipline = (Discipline) o;
        return id == discipline.id
                && Double.compare(credits, discipline.credits) == 0
                && Objects.equals(courseName, discipline.courseName)
                && Objects.equals(description, discipline.description)
                && Objects.equals(type, discipline.type)
                && Objects.equals(lecturer, discipline.lecturer)
                && Objects.equals(assistants, discipline.assistants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, description, credits, type, lecturer, assistants);
    }
}
