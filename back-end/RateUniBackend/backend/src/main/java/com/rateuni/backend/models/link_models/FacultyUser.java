package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.composite_keys.FacultyUserKey;
import jakarta.persistence.*;

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
    private UniUser uniUser;

    public FacultyUser() {

    }

    public FacultyUser(Faculty faculty, UniUser uniUser) {
        this.faculty = faculty;
        this.uniUser = uniUser;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public UniUser getUniversity() {
        return uniUser;
    }

    public void setUniversity(UniUser uniUser) {
        this.uniUser = uniUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyUser that = (FacultyUser) o;
        return Objects.equals(id, that.id) && Objects.equals(faculty, that.faculty) && Objects.equals(uniUser, that.uniUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, uniUser);
    }
}
