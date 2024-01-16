package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DegreeDisciplineKey implements Serializable {
    @Column(name = "degree_id")
    int degreeId;

    @Column(name = "discipline_id")
    int disciplineId;

    public DegreeDisciplineKey() {
    }

    public DegreeDisciplineKey(int degreeId, int disciplineId) {
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
        DegreeDisciplineKey that = (DegreeDisciplineKey) o;
        return degreeId == that.degreeId && disciplineId == that.disciplineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(degreeId, disciplineId);
    }
}
