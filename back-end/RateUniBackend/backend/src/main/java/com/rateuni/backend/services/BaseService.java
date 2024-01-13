package com.rateuni.backend.services;


import com.fasterxml.jackson.databind.ser.Serializers;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.slf4j.event.KeyValuePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class BaseService {
    protected Firestore firestore;

    public BaseService() {
        firestore = FirestoreClient.getFirestore();
    }

    public void updateId(String collectionName) throws ExecutionException, InterruptedException {
        DocumentReference idResult = firestore
                .collection(collectionName)
                .document("Id");

        int prevId = (int) idResult
                .get()
                .get()
                .get("next_id");

        Map<String, Integer> data = new HashMap<>();
        data.put("next_id", prevId + 1);
        idResult.set(data);
    }

    public int getId(String collectionName) throws ExecutionException, InterruptedException {
        DocumentReference idResult = firestore
                .collection(collectionName)
                .document("Id");

        return (int) idResult
                .get()
                .get()
                .get("next_id");
    }
}
