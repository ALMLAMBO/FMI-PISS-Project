package com.rateuni.backend.models.base_models;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.Objects;

public class UniUser {
    private int id;

    private String facultyNumber;

    private String email;

    private String username;

    private String password;

    public String image;

    public UniUser() {
    }

    public UniUser(int id, String facultyNumber, String email, String username, String password, String image) {
        this.id = id;
        this.facultyNumber = facultyNumber;
        this.email = email;
        this.username = username;
        this.password = hashPassword(password);
        this.image = image;
    }

    public int getId() {
        return id;
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
        this.password = hashPassword(password);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    private String hashPassword(String password) {
        return BCrypt.withDefaults()
                .hashToString(BCrypt.SALT_LENGTH, password.toCharArray());
    }
}
