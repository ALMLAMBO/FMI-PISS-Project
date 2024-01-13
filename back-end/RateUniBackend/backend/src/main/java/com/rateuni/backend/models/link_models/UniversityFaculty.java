package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class UniversityFaculty implements Serializable {
    private int universityId;

    private int facultyId;

    public UniversityFaculty() {
    }

    public UniversityFaculty(int universityId, int facultyId) {
        this.universityId = universityId;
        this.facultyId = facultyId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getfacultyId() {
        return facultyId;
    }

    public void setfacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFaculty that = (UniversityFaculty) o;
        return universityId == that.universityId && facultyId == that.facultyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, facultyId);
    }
}
