package com.rateuni.backend.models.base_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Degree {
    private int id;
    private String title;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Degree degree = (Degree) o;
        return id == degree.id
                && Objects.equals(title, degree.title)
                && Objects.equals(description, degree.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }
}
