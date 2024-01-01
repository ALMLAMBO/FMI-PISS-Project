package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.composite_keys.UniversityFacultyKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "universities_faculties")
public class UniversityFaculty {
    @EmbeddedId
    private UniversityFacultyKey id;

    @ManyToOne
    @MapsId("university_id")
    private University university;

    @ManyToOne
    @MapsId("faculty_id")
    private Faculty faculty;

    public UniversityFaculty() {
    }

    public UniversityFaculty(University university, Faculty faculty) {
        this.university = university;
        this.faculty = faculty;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Faculty getfaculty() {
        return faculty;
    }

    public void setfaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFaculty that = (UniversityFaculty) o;
        return Objects.equals(id, that.id) && Objects.equals(university, that.university) && Objects.equals(faculty, that.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, university, faculty);
    }
}
