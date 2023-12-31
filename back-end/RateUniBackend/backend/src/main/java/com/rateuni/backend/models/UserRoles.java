package com.rateuni.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "roles")
public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "role")
    private String role;

    public UserRoles() {
    }

    public UserRoles(int id, String role) {
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
        UserRoles userRoles = (UserRoles) o;
        return id == userRoles.id && Objects.equals(role, userRoles.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }
}
