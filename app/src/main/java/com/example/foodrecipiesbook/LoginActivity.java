package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodrecipiesbook.FireBase.FirebaseClass;

public class LoginActivity extends AppCompatActivity {

    Button logInButton;
    Button signUpButton;
    TextView forgotPassword;

    EditText emailEditText;
    EditText passwordEditText;

    Intent intent;

    FirebaseClass firebaseClass = new FirebaseClass(LoginActivity.this, HomeActivity.class);


    private void navigateTo(Context context, Class navigateToClass){
        intent = new Intent(context, navigateToClass);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInButton = findViewById(R.id.loginBtn);
        signUpButton = findViewById(R.id.signUpButton);
        forgotPassword = findViewById(R.id.forgotPassword);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);



        logInButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            if (email.isEmpty() && password.isEmpty()){
                Toast.makeText(LoginActivity.this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
            }else{
                if (firebaseClass.loginUser(email, password)){
                    navigateTo(LoginActivity.this, HomeActivity.class);
                    finish();
                }

            }
        });

        signUpButton.setOnClickListener(v -> navigateTo(LoginActivity.this, SignUpActivity.class));

        forgotPassword.setOnClickListener(v -> navigateTo(LoginActivity.this, ForgotPasswordActivity.class));

    }
}