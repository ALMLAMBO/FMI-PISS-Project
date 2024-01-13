package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.composite_keys.UserRequestKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_requests")
public class UserRequest {
    @EmbeddedId
    private UserRequestKey id;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    @ManyToOne
    @MapsId("request_id")
    private UniRequest uniRequest;

    public UserRequest() {

    }

    public UserRequest(UniUser user, UniRequest uniRequest) {
        this.user = user;
        this.uniRequest = uniRequest;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
    }

    public UniRequest getUniRequest() {
        return uniRequest;
    }

    public void setUniRequest(UniRequest uniRequest) {
        this.uniRequest = uniRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRequest that = (UserRequest) o;
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && Objects.equals(uniRequest, that.uniRequest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, uniRequest);
    }
}
