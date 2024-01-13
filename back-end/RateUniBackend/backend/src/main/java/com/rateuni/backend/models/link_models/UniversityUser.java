package com.rateuni.backend.models.link_models;

import java.io.Serializable;
import java.util.Objects;

public class UniversityUser implements Serializable {
    private int universityId;

    private int userId;

    public UniversityUser() {
    }

    public UniversityUser(int universityId, int userId) {
        this.universityId = universityId;
        this.userId = userId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityUser that = (UniversityUser) o;
        return universityId == that.universityId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, userId);
    }
}
