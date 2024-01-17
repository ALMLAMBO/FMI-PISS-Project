package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.base_models.Discipline;
import com.rateuni.backend.models.link_models.DegreeDiscipline;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class DisciplineService extends BaseService {
    public Discipline getDiscipline(int disciplineId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.DISCIPLINES_COLLECTION_NAME)
                .whereEqualTo("id", disciplineId)
                .get()
                .get()
                .toObjects(Discipline.class)
                .get(0);
    }

    public void createDiscipline(int degreeId, Discipline discipline) {
        try {
            new DegreeService().getDegree(degreeId);
        }
        catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            long prevId = getId(CollectionsNames.DISCIPLINES_COLLECTION_NAME);
            discipline.setId((int) prevId);
            updateId(CollectionsNames.DISCIPLINES_COLLECTION_NAME);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        firestore
                .collection(CollectionsNames.DISCIPLINES_COLLECTION_NAME)
                .add(discipline);

        firestore
                .collection(CollectionsNames.DEGREES_DISCIPLINES_COLLECTION_NAME)
                .add(new DegreeDiscipline(degreeId, discipline.getId()));
    }
}
