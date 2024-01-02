package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.link_models.DegreeDiscipline;
import com.rateuni.backend.models.link_models.FacultyDegree;
import com.rateuni.backend.repositories.base_repos.DegreeRepository;
import com.rateuni.backend.repositories.link_repos.DegreeDisciplineRepository;
import com.rateuni.backend.repositories.link_repos.FacultyDegreeRepository;
import com.rateuni.backend.repositories.link_repos.UniversityFacultyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DegreeService {
    @Autowired
    private final DegreeRepository degreeRepository;

    @Autowired
    private final FacultyDegreeRepository facultyDegreeRepository;

    @Autowired
    private final DegreeDisciplineRepository degreeDisciplineRepository;

    @Autowired
    private final UniversityFacultyRepository universityFacultyRepository;


    public DegreeService(DegreeRepository degreeRepository,
                         FacultyDegreeRepository facultyDegreeRepository,
                         DegreeDisciplineRepository degreeDisciplineRepository,
                         UniversityFacultyRepository universityFacultyRepository) {

        this.degreeRepository = degreeRepository;
        this.facultyDegreeRepository = facultyDegreeRepository;
        this.degreeDisciplineRepository = degreeDisciplineRepository;
        this.universityFacultyRepository = universityFacultyRepository;
    }

    public List<Degree> getAllDegreesForFaculty(int facultyId) {
        return facultyDegreeRepository
                .findAllById(List.of(facultyId))
                .stream()
                .map(FacultyDegree::getDegree)
                .collect(Collectors.toList());
    }

    public Degree getDegree(int degreeId) {
        return degreeRepository
                .findById(degreeId)
                .get();
    }

    public List<Discipline> getAllDisciplinesForDegree(int degreeId) {
        return degreeDisciplineRepository
                .findAllById(List.of(degreeId))
                .stream()
                .map(DegreeDiscipline::getDiscipline)
                .collect(Collectors.toList());
    }

    @Transactional
    public void addUpdateDegree(int universityId, int facultyId, Degree degree, boolean create) {
       Faculty faculty = universityFacultyRepository
               .findAllById(List.of(universityId))
               .stream()
               .filter(x -> x.getfaculty().getId() == facultyId)
               .findFirst()
               .get()
               .getfaculty();

        if(faculty == null || degree == null) {
            throw new IllegalArgumentException("Degree service - create update: Invalid faculty or university id or degree is null");
        }

        boolean degreeExists = degreeRepository
                .findAll()
                .stream()
                .anyMatch(x -> x.equals(degree));

        if(create && degreeExists) {
            throw new IllegalArgumentException("Degree service - create update: Degree already exists");
        }

        if(!create && !degreeExists) {
            throw new IllegalArgumentException("Degree service - create update: Degree does not exists");
        }

        degreeRepository.save(degree);
        if(create) {
            facultyDegreeRepository.save(new FacultyDegree(faculty, degree));
        }
    }
}
