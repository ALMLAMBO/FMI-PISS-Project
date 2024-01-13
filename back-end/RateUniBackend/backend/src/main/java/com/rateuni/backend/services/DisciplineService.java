package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.Discipline;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void createDiscipline(Discipline discipline) {
        firestore.runAsyncTransaction(x -> {
            try {
                int prevId = getId(CollectionsNames.DISCIPLINES_COLLECTION_NAME);
                discipline.setId(prevId + 1);
                updateId(CollectionsNames.DISCIPLINES_COLLECTION_NAME);
            }
            catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            return firestore
                    .collection(CollectionsNames.DISCIPLINES_COLLECTION_NAME)
                    .add(discipline);
        });
    }
}
