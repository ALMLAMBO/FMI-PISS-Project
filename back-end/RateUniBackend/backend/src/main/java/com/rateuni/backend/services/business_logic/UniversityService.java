package com.rateuni.backend.services.business_logic;

import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.link_models.UniversityUser;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UniversityService extends BaseService {
    public List<University> getAllUniversities() throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.UNIVERSITIES_COLLECTION_NAME)
                .whereGreaterThanOrEqualTo("id", 1)
                .get()
                .get()
                .toObjects(University.class);
    }

    public University getUniversityForUser(int userId) throws ExecutionException, InterruptedException {
        UniversityUser universityUser = firestore
                .collection(CollectionsNames.UNIVERSITIES_USERS_COLLECTION_NAME)
                .whereEqualTo("userId", userId)
                .get()
                .get()
                .toObjects(UniversityUser.class)
                .get(0);

        return getUniversity(universityUser.getUniversityId());
    }

    public University getUniversity(int universityId) throws ExecutionException, InterruptedException {
        return firestore
                .collection(CollectionsNames.UNIVERSITIES_COLLECTION_NAME)
                .whereEqualTo("id", universityId)
                .get()
                .get()
                .toObjects(University.class)
                .get(0);
    }

    public List<UniUser> getAllUsersForUniversity(int universityId) throws ExecutionException, InterruptedException {
        List<UniUser> uniUsers = new ArrayList<>();

        List<UniversityUser> universityUsers = firestore
                .collection(CollectionsNames.UNIVERSITIES_USERS_COLLECTION_NAME)
                .whereEqualTo("universityId", universityId)
                .get()
                .get()
                .toObjects(UniversityUser.class);

        for (UniversityUser universityUser : universityUsers) {
            UniUser user = firestore
                    .collection(CollectionsNames.USERS_COLLECTION_NAME)
                    .whereEqualTo("id", universityUser.getUserId())
                    .get()
                    .get()
                    .toObjects(UniUser.class)
                    .get(0);

            uniUsers.add(user);
        }

        return uniUsers;
    }

    public void createUniversity(University university) throws ExecutionException, InterruptedException {
        try {
            long prevId = getId(CollectionsNames.UNIVERSITIES_COLLECTION_NAME);
            university.setId((int) prevId);
            updateId(CollectionsNames.UNIVERSITIES_COLLECTION_NAME);
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

        firestore
                .collection(CollectionsNames.UNIVERSITIES_COLLECTION_NAME)
                .add(university);
    }
}
