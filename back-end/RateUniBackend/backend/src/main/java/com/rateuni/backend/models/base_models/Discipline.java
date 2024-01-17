package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.UserDiscipline;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "discipline")
    private Set<UserDiscipline> userDisciplines;

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

    public Set<UserDiscipline> getUserDisciplines() {
        return userDisciplines;
    }

    public void setUserDisciplines(Set<UserDiscipline> userDisciplines) {
        this.userDisciplines = userDisciplines;
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
