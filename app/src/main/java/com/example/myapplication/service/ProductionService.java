package com.example.myapplication.service;

import com.example.myapplication.model.Production;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ProductionService {

    private final CollectionReference collection;

    public ProductionService(String vacaId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        UserService userService = new UserService();
        collection = db
                .collection("usuarios")
                .document(userService.getUid())
                .collection("vacas")
                .document(vacaId)
                .collection("producoes");
    }

    public Task<Void> create(Production production) {
        return collection.document().set(production);
    }

    public Task<Void> delete(String prodId) {
        return collection.document(prodId).delete();
    }

    public Task<Void> update(Production production, String productionId) {
        return collection.document(productionId).set(production);
    }

    public Task<QuerySnapshot> getAll() {
        return collection.orderBy("data", Query.Direction.DESCENDING).get();
    }

}
