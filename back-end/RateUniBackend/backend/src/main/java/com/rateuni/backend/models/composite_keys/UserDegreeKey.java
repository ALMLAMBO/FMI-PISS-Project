package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserDegreeKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "degree_id")
    int degreeId;

    public UserDegreeKey() {
    }

    public UserDegreeKey(int userId, int degreeId) {
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
        UserDegreeKey that = (UserDegreeKey) o;
        return userId == that.userId && degreeId == that.degreeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, degreeId);
    }
}
