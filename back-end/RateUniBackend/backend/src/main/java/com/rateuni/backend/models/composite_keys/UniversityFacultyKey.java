package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UniversityFacultyKey implements Serializable {
    @Column(name = "university_id")
    int universityId;

    @Column(name = "faculty_id")
    int facultyId;

    public UniversityFacultyKey() {
    }

    public UniversityFacultyKey(int universityId, int facultyId) {
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
        UniversityFacultyKey that = (UniversityFacultyKey) o;
        return universityId == that.universityId && facultyId == that.facultyId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, facultyId);
    }
}
