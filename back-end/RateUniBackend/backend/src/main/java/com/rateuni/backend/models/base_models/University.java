package com.rateuni.backend.models.base_models;

import com.rateuni.backend.models.link_models.UniversityUsers;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "universities")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "uni_name")
    private String name;

    @Column(name = "rector")
    private String rector;

    @Column(name = "hq_address")
    private String hqAddress;

    @OneToMany(mappedBy = "university")
    Set<UniversityUsers> universityUsers;


    public int getId() {
        return id;
    }

    public University() {
    }

    public University(int id, String name, String rector, String hqAddress) {
        this.id = id;
        this.name = name;
        this.rector = rector;
        this.hqAddress = hqAddress;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRector() {
        return rector;
    }

    public void setRector(String rector) {
        this.rector = rector;
    }

    public String getHqAddress() {
        return hqAddress;
    }

    public void setHqAddress(String hqAddress) {
        this.hqAddress = hqAddress;
    }

    public Set<UniversityUsers> getUniversityUsers() {
        return universityUsers;
    }

    public void setUniversityUsers(Set<UniversityUsers> universityUsers) {
        this.universityUsers = universityUsers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        University that = (University) o;
        return id == that.id
                && Objects.equals(name, that.name)
                && Objects.equals(rector, that.rector)
                && Objects.equals(hqAddress, that.hqAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rector, hqAddress);
    }
}
