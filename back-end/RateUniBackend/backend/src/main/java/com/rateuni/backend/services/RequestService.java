package com.rateuni.backend.services;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.rateuni.backend.models.base_models.UniRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class RequestService {
    private static final String REQUEST_COLLECTION_NAME = "requests";
    private static final String USER_REQUEST_COLLECTION_NAME = "users_requests";

    public void saveRequest(int userId, UniRequest request) {
        Firestore firestore = FirestoreClient.getFirestore();

        ApiFuture<WriteResult> apiFuture = firestore
                .collection(REQUEST_COLLECTION_NAME)
                .document()
                .set(request);
    }

    public List<UniRequest> getAllRequests() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<UniRequest> allRequests = new ArrayList<>();

        Iterable<DocumentReference> requests = firestore
                .collection(REQUEST_COLLECTION_NAME)
                .listDocuments();

        for (DocumentReference documentReference : requests) {
            DocumentSnapshot documentSnapshot = documentReference.get().get();

            UniRequest request = null;
            if(documentSnapshot.exists()) {
                request = documentSnapshot.toObject(UniRequest.class);
                allRequests.add(request);
            }
        }

        return allRequests;
    }
}
