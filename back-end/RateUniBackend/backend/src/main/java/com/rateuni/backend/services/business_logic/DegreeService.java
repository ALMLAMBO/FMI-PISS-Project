package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.base_models.Faculty;
import com.rateuni.backend.models.link_models.DegreeDiscipline;
import com.rateuni.backend.models.link_models.FacultyDegree;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DegreeService extends BaseService {
    private final FacultyService facultyService;

    private final DisciplineService disciplineService;

    public DegreeService() {
        facultyService = new FacultyService();
        disciplineService = new DisciplineService();
    }

    public List<Degree> getAllDegreesForFaculty(int facultyId) throws ExecutionException, InterruptedException {
        List<Degree> degrees = new ArrayList<>();

        List<FacultyDegree> facultyDegrees = firestore
                .collection(CollectionsNames.FACULTIES_DEGREES_COLLECTION_NAME)
                .whereEqualTo("facultyId", facultyId)
                .get()
                .get()
                .toObjects(FacultyDegree.class);

        for (FacultyDegree facultyDegree : facultyDegrees) {
            Degree degree = getDegree(facultyDegree.getDegreeId());

            degrees.add(degree);
        }

        return degrees;
    }

    public Degree getDegreeForUser(int userId, int degreeId) throws ExecutionException, InterruptedException {
        Faculty faculty = facultyService.getFacultyOfUser(userId);
        FacultyDegree facultyDegree = firestore
                .collection(CollectionsNames.FACULTIES_DEGREES_COLLECTION_NAME)
                .whereEqualTo("facultyId", faculty.getId())
                .whereEqualTo("degreeId", degreeId)
                .get()
                .get()
                .toObjects(FacultyDegree.class)
                .get(0);

        return getDegree(degreeId);
    }

    public Degree getDegree(int degreeId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.DEGREES_COLLECTION_NAME)
                .whereEqualTo("id", degreeId)
                .get()
                .get()
                .toObjects(Degree.class)
                .get(0);
    }

    public List<Discipline> getAllDisciplinesForDegree(int degreeId) throws ExecutionException, InterruptedException {
        List<Discipline> disciplines = new ArrayList<>();

        List<DegreeDiscipline> degreeDisciplines = firestore
                .collection(CollectionsNames.DEGREES_DISCIPLINES_COLLECTION_NAME)
                .whereEqualTo("degreeId", degreeId)
                .get()
                .get()
                .toObjects(DegreeDiscipline.class);

        for (DegreeDiscipline degreeDiscipline : degreeDisciplines) {
            Discipline discipline = disciplineService
                    .getDiscipline(degreeDiscipline.getDisciplineId());

            disciplines.add(discipline);
        }

        return disciplines;
    }

    public void addDegree(int facultyId, Degree degree) {
        try {
            facultyService.getFaculty(facultyId);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            long prevId = getId(CollectionsNames.DEGREES_COLLECTION_NAME);
            degree.setId((int) prevId);
            updateId(CollectionsNames.DEGREES_COLLECTION_NAME);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.DEGREES_COLLECTION_NAME)
                .add(degree);

        firestore
                .collection(CollectionsNames.FACULTIES_DEGREES_COLLECTION_NAME)
                .add(new FacultyDegree(facultyId, degree.getId()));
    }
}
