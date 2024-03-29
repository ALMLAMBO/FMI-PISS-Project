package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.link_models.FacultyUser;
import com.rateuni.backend.models.link_models.UniversityFaculty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class FacultyService extends BaseService {
    private UniversityService universityService;

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
            Faculty faculty = getFaculty(universityFaculty.getFacultyId());

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

    public Faculty getFaculty(String faculty) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.FACULTIES_COLLECTION_NAME)
                .whereEqualTo("facultyName", faculty)
                .get()
                .get()
                .toObjects(Faculty.class)
                .get(0);
    }

    public Faculty getFacultyOfUser(int userId) throws ExecutionException, InterruptedException {
        FacultyUser facultyUser = firestore
                .collection(CollectionsNames.FACULTIES_USERS_COLLECTION_NAME)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .toObjects(FacultyUser.class)
                .get(0);

        return getFaculty(facultyUser.getFacultyId());
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
