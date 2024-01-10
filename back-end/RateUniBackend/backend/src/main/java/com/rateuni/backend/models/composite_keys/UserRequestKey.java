package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRequestKey implements Serializable {
    @Column(name = "user_id")
    int userId;

    @Column(name = "request_id")
    int requestId;

    public UserRequestKey() {

    }

    public UserRequestKey(int userId, int requestId) {
        this.userId = userId;
        this.requestId = requestId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequestKey that = (UserRequestKey) o;
        return userId == that.userId && requestId == that.requestId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, requestId);
    }
}
