package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.composite_keys.FacultyUserKey;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "faculties_users")
public class FacultyUser {
    @EmbeddedId
    private FacultyUserKey id;

    @ManyToOne
    @MapsId("faculty_id")
    private Faculty faculty;

    @ManyToOne
    @MapsId("university_id")
    private University university;

    public FacultyUser() {

    }

    public FacultyUser(Faculty faculty, University university) {
        this.faculty = faculty;
        this.university = university;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyUser that = (FacultyUser) o;
        return Objects.equals(id, that.id) && Objects.equals(faculty, that.faculty) && Objects.equals(university, that.university);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, university);
    }
}
