package com.rateuni.backend.models.base_models;

import java.util.Objects;

public class Faculty {
    private int id;

    private String facultyName;

    private String address;

    private String dean;

    public Faculty() {
    }

    public Faculty(int id, String facultyName, String address, String dean) {
        this.id = id;
        this.facultyName = facultyName;
        this.address = address;
        this.dean = dean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfacultyName() {
        return facultyName;
    }

    public void setfacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDean() {
        return dean;
    }

    public void setDean(String dean) {
        this.dean = dean;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty facility = (Faculty) o;
        return id == facility.id
                && Objects.equals(facultyName, facility.facultyName)
                && Objects.equals(address, facility.address)
                && Objects.equals(dean, facility.dean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facultyName, address, dean);
    }
}
