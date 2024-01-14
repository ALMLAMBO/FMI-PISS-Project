package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Degree;
import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.link_models.DegreeDiscipline;
import com.rateuni.backend.models.link_models.FacultyDegree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class DegreeService extends BaseService {
    @Autowired
    private UniversityService universityService;

    @Autowired
    private FacultyService facultyService;

    public List<Degree> getAllDegreesForFaculty(int facultyId) throws ExecutionException, InterruptedException {
        List<Degree> degrees = new ArrayList<>();

        List<FacultyDegree> facultyDegrees = firestore
                .collection(CollectionsNames.FACULTIES_DEGREES_COLLECTION_NAME)
                .whereEqualTo("facultyId", facultyId)
                .get()
                .get()
                .toObjects(FacultyDegree.class);

        for (FacultyDegree facultyDegree : facultyDegrees) {
            Degree degree = firestore
                    .collection(CollectionsNames.DEGREES_COLLECTION_NAME)
                    .whereEqualTo("id", facultyDegree.getDegreeId())
                    .get()
                    .get()
                    .toObjects(Degree.class)
                    .get(0);

            degrees.add(degree);
        }

        return degrees;
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
            Discipline discipline = firestore
                    .collection(CollectionsNames.DISCIPLINES_COLLECTION_NAME)
                    .whereEqualTo("id", degreeDiscipline.getDisciplineId())
                    .get()
                    .get()
                    .toObjects(Discipline.class)
                    .get(0);

            disciplines.add(discipline);
        }

        return disciplines;
    }

    public void addDegree(int universityId, int facultyId, Degree degree) {
        try {
            universityService.getUniversity(universityId);
            facultyService.getFaculty(facultyId);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore.runAsyncTransaction(x -> {
            try {
                int prevId = getId(CollectionsNames.DEGREES_COLLECTION_NAME);
                degree.setId(prevId + 1);
                updateId(CollectionsNames.DEGREES_COLLECTION_NAME);
            }
            catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            firestore
                    .collection(CollectionsNames.DEGREES_COLLECTION_NAME)
                    .add(degree);

            return firestore
                    .collection(CollectionsNames.FACULTIES_DEGREES_COLLECTION_NAME)
                    .add(new FacultyDegree(facultyId, degree.getId()));
        });
    }
}
