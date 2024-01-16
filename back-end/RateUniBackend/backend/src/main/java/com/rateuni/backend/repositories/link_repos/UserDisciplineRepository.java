package com.rateuni.backend.repositories.link_repos;

import com.rateuni.backend.models.link_models.UserDiscipline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDisciplineRepository extends JpaRepository<UserDiscipline, Integer> {
}
