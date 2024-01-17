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
public class Discipline {
    private int id;
    private String courseName;
    private String description;
    private double credits;
    private String type;
    private String lecturer;
    private String assistants;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline discipline = (Discipline) o;
        return id == discipline.id
                && Double.compare(credits, discipline.credits) == 0
                && Objects.equals(courseName, discipline.courseName)
                && Objects.equals(description, discipline.description)
                && Objects.equals(type, discipline.type)
                && Objects.equals(lecturer, discipline.lecturer)
                && Objects.equals(assistants, discipline.assistants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseName, description, credits, type, lecturer, assistants);
    }
}
