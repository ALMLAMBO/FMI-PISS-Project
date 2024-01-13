package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class FacultyUser implements Serializable {
    private int facultyId;

    private int userId;

    public FacultyUser() {

    }

    public FacultyUser(int facultyId, int userId) {
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
        FacultyUser that = (FacultyUser) o;
        return facultyId == that.facultyId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, userId);
    }
}
