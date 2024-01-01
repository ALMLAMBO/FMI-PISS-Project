package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.Facility;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.composite_keys.UniversityFacilityKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "university_facilities")
public class UniversityFacilities {
    @EmbeddedId
    private UniversityFacilityKey id;

    @ManyToOne
    @MapsId("university_id")
    private University university;

    @ManyToOne
    @MapsId("facility_id")
    private Facility facility;

    public UniversityFacilities() {
    }

    public UniversityFacilities(University university, Facility facility) {
        this.university = university;
        this.facility = facility;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public Facility getFacility() {
        return facility;
    }

    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFacilities that = (UniversityFacilities) o;
        return Objects.equals(id, that.id) && Objects.equals(university, that.university) && Objects.equals(facility, that.facility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, university, facility);
    }
}
