package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.link_models.ReviewDiscipline;
import com.rateuni.backend.repositories.base_repos.DisciplineRepository;
import com.rateuni.backend.repositories.base_repos.ReviewRepository;
import com.rateuni.backend.repositories.link_repos.ReviewDisciplineRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final ReviewDisciplineRepository reviewDisciplineRepository;

    @Autowired
    private final DisciplineRepository disciplineRepository;



    public ReviewService(ReviewRepository reviewRepository,
                         ReviewDisciplineRepository reviewDisciplineRepository,
                         DisciplineRepository disciplineRepository) {

        this.reviewRepository = reviewRepository;
        this.reviewDisciplineRepository = reviewDisciplineRepository;
        this.disciplineRepository = disciplineRepository;
    }

    public List<Review> getAllReviewsForDiscipline(int disciplineId) {
        Discipline discipline = disciplineRepository
                .findById(disciplineId)
                .get();

        if(discipline == null) {
            throw new IllegalArgumentException("Review service - get all reviews: Invalid discipline id");
        }

        return reviewDisciplineRepository
                .findAllById(List.of(disciplineId))
                .stream()
                .map(ReviewDiscipline::getReview)
                .collect(Collectors.toList());
    }

    public Review getReview(int reviewId) {
        return reviewRepository
                .findById(reviewId)
                .get();
    }

    @Transactional
    public void createReview(int disciplineId, Review review) {
        Discipline discipline = disciplineRepository
                .findById(disciplineId)
                .get();

        if(discipline == null) {
            throw new IllegalArgumentException("Review service - create: Invalid discipline id");
        }

        reviewRepository.save(review);
        reviewDisciplineRepository.save(new ReviewDiscipline(discipline, review));
    }
}
