package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.composite_keys.UserDisciplineKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_disciplines")
public class UserDiscipline {
    @EmbeddedId
    private UserDisciplineKey id;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    @ManyToOne
    @MapsId("discipline_id")
    private Discipline discipline;

    public UserDiscipline() {
    }

    public UserDiscipline(UniUser user, Discipline discipline) {
        this.user = user;
        this.discipline = discipline;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDiscipline that = (UserDiscipline) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(discipline, that.discipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, discipline);
    }
}
