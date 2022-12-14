package com.example.foodrecipiesbook.FireBase;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseClass {

    private static final String TAG = "EmailPassword";
    private final FirebaseAuth mAuth;
    private final Context context;
    private final Class activity;

    private Boolean flag = false;


    public FirebaseClass(Context context, Class activity) {
        this.context = context;
        this.activity = activity;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signUpUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context, "Your Account Successfully Created",
                        Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, activity));
                flag = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(),
                        Toast.LENGTH_SHORT).show();
                flag = false;
            }
        });
    }
    public Boolean loginUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(context, "Logged In Successfully", Toast.LENGTH_SHORT).show();
                context.startActivity(new Intent(context, activity));
                flag = true;

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                flag = false;
            }
        });
        return flag;
    }

    public void passwordReset(String email){
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(context, "Please check your Email!", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Failed! Please Try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void signOutUser(){
        mAuth.signOut();
    }

//    public void registerUser(String email, String password) {
//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if (task.isSuccessful()) {
//                    // Sign in success, update UI with the signed-in user's information
//                    Log.d(TAG, "createUserWithEmail:success");
//                    FirebaseUser user = mAuth.getCurrentUser();
//                    Toast.makeText(context, "Registered Successful.",
//                            Toast.LENGTH_SHORT).show();
//                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//                        Toast.makeText(context, "User with this email already exist.", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    // If sign in fails, display a message to the user.
//                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                    Toast.makeText(context, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }


}

