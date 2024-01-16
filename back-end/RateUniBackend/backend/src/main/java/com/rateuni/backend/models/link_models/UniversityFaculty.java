package com.rateuni.backend.models.link_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityFaculty implements Serializable {
    private int universityId;
    private int facultyId;

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
