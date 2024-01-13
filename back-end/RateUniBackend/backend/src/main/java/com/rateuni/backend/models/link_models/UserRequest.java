package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.UniUser;
import jakarta.persistence.*;

import java.util.Objects;

public class UserRequest {
    private int requestId;

    private String username;

    private String university;

    private String faculty;

    private String degree;

    private String facultyNumber;

    private String status;

    private boolean approved;

    public UserRequest() {

    }

    public UserRequest(int requestId, String username, String university,
                       String faculty, String degree, String facultyNumber, String status, boolean approved) {

        this.requestId = requestId;
        this.username = username;
        this.university = university;
        this.faculty = faculty;
        this.degree = degree;
        this.facultyNumber = facultyNumber;
        this.status = status;
        this.approved = approved;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
}
