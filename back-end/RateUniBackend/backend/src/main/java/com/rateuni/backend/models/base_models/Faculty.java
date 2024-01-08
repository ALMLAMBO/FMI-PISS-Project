package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.UniversityFaculty;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "faculties")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "faculty_name")
    private String facultyName;

    @Column(name = "address")
    private String address;

    @Column(name = "dean")
    private String dean;

    @OneToMany(mappedBy = "faculty")
    private Set<UniversityFaculty> universityFacilities;

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

    public Set<UniversityFaculty> getUniversityFacilities() {
        return universityFacilities;
    }

    public void setUniversityFacilities(Set<UniversityFaculty> universityFacilities) {
        this.universityFacilities = universityFacilities;
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
