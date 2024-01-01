package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.composite_keys.UserDegreeKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_degrees")
public class UserDegree {
    @EmbeddedId
    private UserDegreeKey id;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    @ManyToOne
    @MapsId("degree_id")
    private Degree degree;

    public UserDegree() {
    }

    public UserDegree(UniUser user, Degree degree) {
        this.user = user;
        this.degree = degree;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
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
        UserDegree that = (UserDegree) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(degree, that.degree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, degree);
    }
}
