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
public class UniversityUser implements Serializable {
    private int universityId;
    private int userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityUser that = (UniversityUser) o;
        return universityId == that.universityId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, userId);
    }
}
