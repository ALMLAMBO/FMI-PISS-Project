package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FacultyDegreeKey implements Serializable {
    @Column(name = "faculty_id")
    int facultyId;

    @Column(name = "degree_id")
    int degreeId;

    public FacultyDegreeKey() {
    }

    public FacultyDegreeKey(int facultyId, int degreeId) {
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
        FacultyDegreeKey that = (FacultyDegreeKey) o;
        return facultyId == that.facultyId && degreeId == that.degreeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, degreeId);
    }
}
