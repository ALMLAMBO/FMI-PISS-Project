package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserDisciplineKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "discipline_id")
    int disciplineId;

    public UserDisciplineKey() {
    }

    public UserDisciplineKey(int userId, int disciplineId) {
        this.userId = userId;
        this.disciplineId = disciplineId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        UserDisciplineKey that = (UserDisciplineKey) o;
        return userId == that.userId && disciplineId == that.disciplineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, disciplineId);
    }
}
