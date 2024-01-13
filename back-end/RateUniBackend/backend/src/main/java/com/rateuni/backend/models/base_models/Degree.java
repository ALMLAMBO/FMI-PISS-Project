package com.rateuni.backend.models.base_models;

import java.util.Objects;

public class Degree {
    private int id;

    private String title;

    private String description;

    public Degree() {
    }

    public Degree(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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
