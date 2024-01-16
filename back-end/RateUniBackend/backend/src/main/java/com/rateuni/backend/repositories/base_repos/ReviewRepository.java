package com.rateuni.backend.repositories.base_repos;

import com.rateuni.backend.models.base_models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
