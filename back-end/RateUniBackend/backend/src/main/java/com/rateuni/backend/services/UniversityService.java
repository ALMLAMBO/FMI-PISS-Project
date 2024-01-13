package com.rateuni.backend.services;

import com.rateuni.backend.models.base_models.UniUser;
import com.rateuni.backend.models.base_models.University;
import com.rateuni.backend.models.link_models.UniversityUser;
import com.rateuni.backend.repositories.base_repos.UniversityRepository;
import jakarta.transaction.Transactional;
import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
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
                .whereEqualTo("university_id", universityId)
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
        University uni = getUniversity(university.getId());

        if(uni != null) {
            throw new IllegalIdentifierException("University Service create uni: University already exists");
        }

        firestore.runAsyncTransaction(x -> {
            try {
                int prevId = getId(CollectionsNames.UNIVERSITIES_COLLECTION_NAME);
                university.setId(prevId + 1);
                updateId(CollectionsNames.UNIVERSITIES_COLLECTION_NAME);

                return firestore
                        .collection(CollectionsNames.UNIVERSITIES_COLLECTION_NAME)
                        .add(university);
            }
            catch (ExecutionException | InterruptedException e) {
                System.out.println(e.getMessage());
                return null;
            }
        });
    }
}
