package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class FacultyDegree implements Serializable {
    private int facultyId;

    private int degreeId;

    public FacultyDegree() {
    }

    public FacultyDegree(int facultyId, int degreeId) {
        this.facultyId = facultyId;
        this.degreeId = degreeId;
    }

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FacultyDegree that = (FacultyDegree) o;
        return facultyId == that.facultyId && degreeId == that.degreeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, degreeId);
    }
}
