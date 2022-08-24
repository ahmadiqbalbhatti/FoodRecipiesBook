package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    Button logInButton;
    Button signUpButton;
    TextView forgotPassword;

    TextView emailTextView;
    TextView passwordTextView;

    Intent intent;

    private FirebaseAuth mAuth;

    private void navigateTo(Context context, Class navigateToClass){
        intent = new Intent(context, navigateToClass);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logInButton = findViewById(R.id.logInButton);
        signUpButton = findViewById(R.id.signUpButton);
        forgotPassword = findViewById(R.id.forgotPassword);

        emailTextView = findViewById(R.id.emailTextView);
        passwordTextView = findViewById(R.id.passwordTextView);


        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (emailTextView.getText().toString().isEmpty() && passwordTextView.getText().toString().isEmpty()){
//                    Toast.makeText(LoginActivity.this, "Please Enter Email and Password", Toast.LENGTH_SHORT).show();
//                }else{
//                    navigateTo(LoginActivity.this, HomeActivity.class);
//                }
                navigateTo(LoginActivity.this, HomeActivity.class);

            }
        });





        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.this, SignUpActivity.class);
            }
        });


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateTo(LoginActivity.this, ForgotPasswordActivity.class);
            }
        });

    }
}