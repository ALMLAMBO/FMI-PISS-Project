package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.composite_keys.DegreeDisciplineKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "degrees_disciplines")
public class DegreeDiscipline {
    @EmbeddedId
    private DegreeDisciplineKey id;

    @ManyToOne
    @MapsId("discipline_id")
    private Discipline discipline;

    @ManyToOne
    @MapsId("degree_id")
    private Degree degree;

    public DegreeDiscipline() {
    }

    public DegreeDiscipline(Discipline discipline, Degree degree) {
        this.discipline = discipline;
        this.degree = degree;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DegreeDiscipline that = (DegreeDiscipline) o;
        return Objects.equals(id, that.id) && Objects.equals(discipline, that.discipline) && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discipline, degree);
    }
}
