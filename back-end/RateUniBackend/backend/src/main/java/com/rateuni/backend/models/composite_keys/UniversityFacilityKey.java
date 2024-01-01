package com.rateuni.backend.models.composite_keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UniversityFacilityKey implements Serializable {
    @Column(name = "university_id")
    int universityId;

    @Column(name = "facility_id")
    int facilityId;

    public UniversityFacilityKey() {
    }

    public UniversityFacilityKey(int universityId, int facilityId) {
        this.universityId = universityId;
        this.facilityId = facilityId;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public int getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityFacilityKey that = (UniversityFacilityKey) o;
        return universityId == that.universityId && facilityId == that.facilityId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(universityId, facilityId);
    }
}
