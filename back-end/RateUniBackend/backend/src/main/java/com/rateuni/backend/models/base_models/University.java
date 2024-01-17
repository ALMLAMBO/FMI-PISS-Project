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
public class University {
    private int id;
    private String name;
    private String rector;
    private String hqAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(rector, that.rector)
                && Objects.equals(hqAddress, that.hqAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rector, hqAddress);
    }
}
