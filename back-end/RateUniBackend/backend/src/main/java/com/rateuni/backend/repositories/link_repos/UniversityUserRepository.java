package com.rateuni.backend.repositories.link_repos;

import com.rateuni.backend.models.link_models.UniversityUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityUserRepository extends JpaRepository<UniversityUser, Integer> {
}
