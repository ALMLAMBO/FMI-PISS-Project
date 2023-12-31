package com.rateuni.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "uni_users")
public class UniUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "fn")
    private String facultyNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public UniUser() {
    }

    public UniUser(int id, String facultyNumber, String email, String username, String password) {
        this.id = id;
        this.facultyNumber = facultyNumber;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniUser uniUser = (UniUser) o;
        return id == uniUser.id
                && Objects.equals(facultyNumber, uniUser.facultyNumber)
                && Objects.equals(email, uniUser.email)
                && Objects.equals(username, uniUser.username)
                && Objects.equals(password, uniUser.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyNumber, email, username, password);
    }
}
