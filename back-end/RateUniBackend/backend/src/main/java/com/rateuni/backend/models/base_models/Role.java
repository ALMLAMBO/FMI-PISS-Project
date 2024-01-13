package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.UserRole;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

public class Role {
    private int id;

    private String role;

    public Role() {
    }

    public Role(int id, String role) {
        this.id = id;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role userRoles = (Role) o;
        return id == userRoles.id && Objects.equals(role, userRoles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
