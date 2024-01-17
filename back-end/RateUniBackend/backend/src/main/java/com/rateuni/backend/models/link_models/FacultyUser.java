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
public class FacultyUser implements Serializable {
    private int facultyId;
    private int userId;

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
