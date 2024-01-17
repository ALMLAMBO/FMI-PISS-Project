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
public class Faculty {
    private int id;
    private String facultyName;
    private String address;
    private String dean;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty facility = (Faculty) o;
        return id == facility.id
                && Objects.equals(facultyName, facility.facultyName)
                && Objects.equals(address, facility.address)
                && Objects.equals(dean, facility.dean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyName, address, dean);
    }
}
