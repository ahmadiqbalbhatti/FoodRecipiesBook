package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Objects;

public class RecipeDetailActivity extends AppCompatActivity {

    Boolean isFavorite = true;

    String detail = "Eggs, Rice, White rice chicken biryani Biryani masala Chicken, Eggs, Rice, White rice chicken biryani Biryani \n\nmasala Chicken, Eggs, Rice, White rice chicken biryani Biryani masala Chicken, Eggs, Rice, White rice chicken biryani Biryani masala Chicken, Eggs, Rice, White rice chicken biryani Biryani masala";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Start Cooking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        LinearLayout linearLayout = findViewById(R.id.favIcon);
//        ImageView favImage = findViewById(R.id.favoriteImage);


        ImageView headerImage = findViewById(R.id.headerImage);
        TextView recipeTitle = findViewById(R.id.recipeTitle);
        TextView recipeCookingTime = findViewById(R.id.cookingTime);
        TextView recipeIngredientsList = findViewById(R.id.ingredientsList);
        TextView recipeDetail = findViewById(R.id.description);

        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("ingredients") &&
                intent.hasExtra("imageId") && intent.hasExtra("time")){
            recipeTitle.setText(intent.getStringExtra("title"));
            recipeCookingTime.setText(intent.getStringExtra("time"));
            recipeIngredientsList.setText(intent.getStringExtra("ingredients"));

            headerImage.setImageResource(intent.getIntExtra("imageId", 700009));
            recipeDetail.setText(intent.getStringExtra("method"));
        }

//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if (!isFavorite){
//                    favImage.setImageResource(R.drawable.favorite);
//                    isFavorite=true;
//                }
//                else {
//                    favImage.setImageResource(R.drawable.solid_favorite);
//                    isFavorite=false;
//                }
//
//            }
//        });

    }
}