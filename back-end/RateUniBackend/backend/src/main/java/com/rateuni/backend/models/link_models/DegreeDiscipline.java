package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class DegreeDiscipline implements Serializable {
    private int degreeId;

    private int disciplineId;

    public DegreeDiscipline() {
    }

    public DegreeDiscipline(int degreeId, int disciplineId) {
        this.degreeId = degreeId;
        this.disciplineId = disciplineId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    public int getDisciplineId() {
        return disciplineId;
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId = disciplineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeDiscipline that = (DegreeDiscipline) o;
        return degreeId == that.degreeId && disciplineId == that.disciplineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeId, disciplineId);
    }
}
