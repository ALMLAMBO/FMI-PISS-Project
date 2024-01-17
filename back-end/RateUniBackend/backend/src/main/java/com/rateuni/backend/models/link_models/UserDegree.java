package com.rateuni.backend.models.link_models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDegree implements Serializable {
    private int userId;
    private int degreeId;

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
