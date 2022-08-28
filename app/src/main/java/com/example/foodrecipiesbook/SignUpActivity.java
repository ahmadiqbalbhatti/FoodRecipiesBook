package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodrecipiesbook.FireBase.FirebaseClass;

public class SignUpActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        FirebaseClass firebaseClass = new FirebaseClass(SignUpActivity.this,
                HomeActivity.class);


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
//                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                firebaseClass.signUpUser(email, password);

                finish();
            }
        });
    }
}