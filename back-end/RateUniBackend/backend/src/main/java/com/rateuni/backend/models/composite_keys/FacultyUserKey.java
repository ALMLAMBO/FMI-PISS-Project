package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FacultyUserKey implements Serializable {
    @Column(name = "faculty_id")
    int facultyId;

    @Column(name = "user_id")
    int userId;

    public FacultyUserKey() {

    }

    public FacultyUserKey(int facultyId, int userId) {
        this.facultyId = facultyId;
        this.userId = userId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyUserKey that = (FacultyUserKey) o;
        return facultyId == that.facultyId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, userId);
    }
}
