package com.example.foodrecipiesbook.FireBase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class FirebaseFirestoreClass {
    FirebaseFirestore fireDataBase = FirebaseFirestore.getInstance();

    Context context;

    public FirebaseFirestoreClass(Context context) {
        this.context = context;
    }

    /**
     * Add new user or recipe item in firebase
     * FireStore database
     * **/
    public void addNewItem(String collection, Object dataObject){
        fireDataBase.collection(collection).add(dataObject).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(context, "Record Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Read a complete collection on database
     * **/
    public Task<QuerySnapshot> readItem(String collectionName){
        return fireDataBase.collection(collectionName).get();
    }

    /**
     * Read a complete document of given Document ID
     * **/
    public  Task<DocumentSnapshot> readItem(String collectionName,
                                            String documentId){
        return fireDataBase.collection(collectionName).document(documentId).get();
    }

    /**
     * Use to filter result as per query
     * **/
    public Task<QuerySnapshot> filter(String collection, String field, String query){
        return fireDataBase.collection(collection).whereEqualTo(field,query).get();
    }

    /**
     * Use to Update data from FireStore database
     * **/
    public void Update (String collection, String documentId, Object data){
        fireDataBase.collection(collection).document(documentId).set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Updated", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    /**
     * Use to Delete data from FireStore database
     * **/

    public void Delete(String collection,String DocumentId) {
        fireDataBase.collection(collection).document(DocumentId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Record Deleted", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
