package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                if (password.length()>6){
                    firebaseFirestoreClass.addNewItem("users", user);
                    firebaseClass.signUpUser(email, password);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Password must be " +
                                    "greater than 6 character",
                            Toast.LENGTH_SHORT).show();
                }

                finish();
            }
        });
    }
}