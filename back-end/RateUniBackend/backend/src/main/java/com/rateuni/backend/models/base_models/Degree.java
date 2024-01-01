package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.FacultyDegree;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "degrees")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "degree")
    private Set<FacultyDegree> facultyDegrees;

    public Degree() {
    }

    public Degree(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<FacultyDegree> getFacultyDegrees() {
        return facultyDegrees;
    }

    public void setFacultyDegrees(Set<FacultyDegree> facultyDegrees) {
        this.facultyDegrees = facultyDegrees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return id == degree.id
                && Objects.equals(title, degree.title)
                && Objects.equals(description, degree.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
