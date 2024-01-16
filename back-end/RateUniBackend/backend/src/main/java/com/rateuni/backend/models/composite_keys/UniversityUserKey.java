package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UniversityUserKey implements Serializable {
    @Column(name = "university_id")
    int universityId;

    @Column(name = "user_id")
    int userId;

    public UniversityUserKey() {
    }

    public UniversityUserKey(int universityId, int userId) {
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
        UniversityUserKey that = (UniversityUserKey) o;
        return universityId == that.universityId && userId == that.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, userId);
    }
}
