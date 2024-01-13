package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class UserDiscipline implements Serializable {
    private int userId;

    private int disciplineId;

    public UserDiscipline() {
    }

    public UserDiscipline(int userId, int disciplineId) {
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
        UserDiscipline that = (UserDiscipline) o;
        return userId == that.userId && disciplineId == that.disciplineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, disciplineId);
    }
}
