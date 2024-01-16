package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.composite_keys.FacultyDegreeKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "faculties_degrees")
public class FacultyDegree {
    @EmbeddedId
    private FacultyDegreeKey id;

    @ManyToOne
    @MapsId("faculty_id")
    private Faculty faculty;

    @ManyToOne
    @MapsId("degree_id")
    private Degree degree;

    public FacultyDegree() {
    }

    public FacultyDegree(Faculty faculty, Degree degree) {
        this.faculty = faculty;
        this.degree = degree;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDegree that = (FacultyDegree) o;
        return Objects.equals(id, that.id) && Objects.equals(faculty, that.faculty) && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, degree);
    }
}
