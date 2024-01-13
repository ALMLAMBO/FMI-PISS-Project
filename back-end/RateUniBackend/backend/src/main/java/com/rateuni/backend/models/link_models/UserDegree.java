package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class UserDegree implements Serializable {
    private int userId;

    private int degreeId;

    public UserDegree() {
    }

    public UserDegree(int userId, int degreeId) {
        this.userId = userId;
        this.degreeId = degreeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(int degreeId) {
        this.degreeId = degreeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDegree that = (UserDegree) o;
        return userId == that.userId && degreeId == that.degreeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, degreeId);
    }
}
