package com.rateuni.backend.models.link_models;

import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.composite_keys.UniversityUserKey;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "university_users")
public class UniversityUsers {
    @EmbeddedId
    private UniversityUserKey id;

    @ManyToOne
    @MapsId("university_id")
    private University university;

    @ManyToOne
    @MapsId("user_id")
    private UniUser user;

    public UniversityUsers() {
    }

    public UniversityUsers(University university, UniUser user) {
        this.university = university;
        this.user = user;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public UniUser getUser() {
        return user;
    }

    public void setUser(UniUser user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityUsers that = (UniversityUsers) o;
        return Objects.equals(id, that.id) && Objects.equals(university, that.university) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, university, user);
    }
}
