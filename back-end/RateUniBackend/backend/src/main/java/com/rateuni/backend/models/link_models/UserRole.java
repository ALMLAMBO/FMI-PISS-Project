package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Role;
import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.composite_keys.UserRoleKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users_roles")
public class UserRole {
    @EmbeddedId
    private UserRoleKey id;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    @ManyToOne
    @MapsId("role_id")
    private Role role;

    public UserRole() {
    }

    public UserRole(UniUser user, Role role) {
        this.user = user;
        this.role = role;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRoles = (UserRole) o;
        return Objects.equals(id, userRoles.id) && Objects.equals(user, userRoles.user) && Objects.equals(role, userRoles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, role);
    }
}
