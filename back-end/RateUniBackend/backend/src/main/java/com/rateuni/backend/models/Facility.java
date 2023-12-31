package com.rateuni.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "facilities")
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "facility_name")
    private String facilityName;

    @Column(name = "address")
    private String address;

    @Column(name = "dean")
    private String dean;

    public Facility() {
    }

    public Facility(int id, String facilityName, String address, String dean) {
        this.id = id;
        this.facilityName = facilityName;
        this.address = address;
        this.dean = dean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
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
        Facility facility = (Facility) o;
        return id == facility.id
                && Objects.equals(facilityName, facility.facilityName)
                && Objects.equals(address, facility.address)
                && Objects.equals(dean, facility.dean);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, facilityName, address, dean);
    }
}
