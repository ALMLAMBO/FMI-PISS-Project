package com.rateuni.backend.repositories.base_repos;

import com.rateuni.backend.models.base_models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree, Integer> {
}
