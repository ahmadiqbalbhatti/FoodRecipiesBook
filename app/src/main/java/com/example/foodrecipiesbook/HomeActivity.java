package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    CardView fastFoodRecipesCardView;

    androidx.appcompat.widget.Toolbar myToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myToolbar = findViewById(R.id.myToolBar);


        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Food Recipe Book");

        fastFoodRecipesCardView = findViewById(R.id.fastFoodRecipes);
        
        fastFoodRecipesCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "I'm clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(HomeActivity.this, RecipesListActivity.class);
                startActivity(intent);
            }
        });

        
    }
}