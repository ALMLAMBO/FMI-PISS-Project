package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.link_models.UniversityFaculty;
import com.rateuni.backend.repositories.base_repos.FacultyRepository;
import com.rateuni.backend.repositories.base_repos.UniversityRepository;
import com.rateuni.backend.repositories.link_repos.UniversityFacultyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    @Autowired
    private final FacultyRepository facultyRepository;

    @Autowired
    private final UniversityFacultyRepository universityFacultyRepository;

    @Autowired
    private final UniversityRepository universityRepository;

    public FacultyService(FacultyRepository facultyRepository,
                          UniversityFacultyRepository universityFacultyRepository,
                          UniversityRepository universityRepository) {

        this.facultyRepository = facultyRepository;
        this.universityFacultyRepository = universityFacultyRepository;
        this.universityRepository = universityRepository;
    }

    public List<Faculty> getAllFacultiesForUniversity(int universityId) {
        return universityFacultyRepository
                .findAllById(List.of(universityId))
                .stream()
                .map(UniversityFaculty::getfaculty)
                .collect(Collectors.toList());
    }

    public Faculty getFaculty(int facultyId) {
        return facultyRepository
                .findById(facultyId)
                .get();
    }

    @Transactional
    public void createUpdateFaculty(int universityId, Faculty faculty, boolean create) {
        University university = universityRepository
                .findById(universityId)
                .get();

        if(university == null || faculty == null) {
            throw new IllegalArgumentException("Faculty service create faculty: Invalid university id");
        }

        if(!create) {
            UniversityFaculty universityFaculty = universityFacultyRepository
                    .findAllById(List.of(universityId))
                    .stream()
                    .filter(x -> x.getfaculty().getId() == faculty.getId())
                    .findFirst()
                    .get();

            if(universityFaculty != null) {
                throw new IllegalArgumentException("Faculty service create faculty: faculty already exists");
            }
        }

        facultyRepository.save(faculty);
        if(create) {
            universityFacultyRepository.save(new UniversityFaculty(university, faculty));
        }
    }
}
