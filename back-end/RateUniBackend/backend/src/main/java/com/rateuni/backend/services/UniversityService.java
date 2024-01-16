package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.repositories.base_repos.UniversityRepository;
import jakarta.transaction.Transactional;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UniversityService {
    @Autowired
    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public List<University> getAllUniversities() {
        return universityRepository.findAll();
    }

    public University getUniversity(int universityId) {
        return universityRepository
                .findById(universityId)
                .get();
    }

    @Transactional
    public void createUniversity(University university) {
        boolean universityExists = universityRepository
                .existsById(university.getId());

        if(universityExists) {
            throw new IllegalIdentifierException("University Service create uni: University already exists");
        }

        universityRepository.save(university);
    }
}
