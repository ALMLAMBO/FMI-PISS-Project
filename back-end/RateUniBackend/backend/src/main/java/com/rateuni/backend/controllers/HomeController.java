package com.rateuni.backend.controllers;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.base_models.Review;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.request_response.request.UniversityIdRequest;
import com.rateuni.backend.services.business_logic.DegreeService;
import com.rateuni.backend.services.business_logic.FacultyService;
import com.rateuni.backend.services.business_logic.ReviewService;
import com.rateuni.backend.services.business_logic.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/home")
@CrossOrigin
@RequiredArgsConstructor
public class HomeController {
    private final ReviewService reviewService;
    private final UniversityService universityService;
    private final FacultyService facultyService;
    private final DegreeService degreeService;

    @GetMapping("/get-reviews")
    public ResponseEntity<List<Review>> getReviews() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    @GetMapping("/get-universities")
    public ResponseEntity<List<University>> getAllUniversities() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(universityService.getAllUniversities());
    }

    @GetMapping("/get-faculties/{id}")
    public ResponseEntity<List<Faculty>> getFacultiesForUniversity(@PathVariable int id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(facultyService.getAllFacultiesForUniversity(id));
    }

    @GetMapping("/get-degrees/{id}")
    public ResponseEntity<List<Degree>> getDegreesForFaculty(@PathVariable int id) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(degreeService.getAllDegreesForFaculty(id));
    }
}
