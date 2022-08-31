package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodrecipiesbook.FireBase.FirebaseClass;
import com.example.foodrecipiesbook.FireBase.FirebaseFirestoreClass;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText, nameEditText, usernameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        FirebaseClass firebaseClass = new FirebaseClass(SignUpActivity.this,
                HomeActivity.class);

        FirebaseFirestoreClass firebaseFirestoreClass =
                new FirebaseFirestoreClass(SignUpActivity.this);

        HashMap<String, Object> user = new HashMap<>();


        nameEditText = findViewById(R.id.newName);
        usernameEditText = findViewById(R.id.newUsername);
        emailEditText = findViewById(R.id.newEmail);
        passwordEditText = findViewById(R.id.newPassword);


        Button logInButton = findViewById(R.id.logInButton);
        Button signUpButton = findViewById(R.id.signUp);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String username = usernameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                user.put("name", name);
                user.put("username", username);
                user.put("email", email);
                user.put("password", password);

                // Use to create user login id
                firebaseFirestoreClass.addNewItem("users", user);
                firebaseClass.signUpUser(email, password);



                finish();
            }
        });
    }
}