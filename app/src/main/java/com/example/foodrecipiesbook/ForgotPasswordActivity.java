package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.foodrecipiesbook.FireBase.FirebaseClass;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        FirebaseClass firebaseClass = new FirebaseClass(ForgotPasswordActivity.this, null);

        TextView loginTextView = findViewById(R.id.loginTextView);
        Button resetPassword = findViewById(R.id.resetPasswordButton);
        emailEditText = findViewById(R.id.resetEmail);

        loginTextView.setOnClickListener(v -> {
//                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
            finish();
        });

        resetPassword.setOnClickListener(v -> {
            firebaseClass.passwordReset(emailEditText.getText().toString());
            finish();
        });



    }
}