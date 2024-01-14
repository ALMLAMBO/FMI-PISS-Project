package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.link_models.UniversityFaculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FacultyService extends BaseService {
    private final UniversityService universityService;

    public FacultyService() {
        universityService = new UniversityService();
    }

    public List<Faculty> getAllFacultiesForUniversity(int universityId) throws ExecutionException, InterruptedException {
        List<Faculty> faculties = new ArrayList<>();

        List<UniversityFaculty> universityFaculties = firestore
                .collection(CollectionsNames.UNIVERSITIES_FACULTIES_COLLECTION_NAME)
                .whereEqualTo("universityId", universityId)
                .get()
                .get()
                .toObjects(UniversityFaculty.class);

        for (UniversityFaculty universityFaculty : universityFaculties) {
            Faculty faculty = getFaculty(universityFaculty.getfacultyId());

            faculties.add(faculty);
        }

        return faculties;
    }

    public Faculty getFaculty(int facultyId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.FACULTIES_COLLECTION_NAME)
                .whereEqualTo("id", facultyId)
                .get()
                .get()
                .toObjects(Faculty.class)
                .get(0);
    }


    public void createFaculty(int universityId, Faculty faculty) {
        try {
            universityService.getUniversity(universityId);
        }
        catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        try {
            long prevId = getId(CollectionsNames.FACULTIES_COLLECTION_NAME);
            faculty.setId((int) prevId);
            updateId(CollectionsNames.FACULTIES_COLLECTION_NAME);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.FACULTIES_COLLECTION_NAME)
                .add(faculty);

        firestore
                .collection(CollectionsNames.UNIVERSITIES_FACULTIES_COLLECTION_NAME)
                .add(new UniversityFaculty(universityId, faculty.getId()));
    }
}
