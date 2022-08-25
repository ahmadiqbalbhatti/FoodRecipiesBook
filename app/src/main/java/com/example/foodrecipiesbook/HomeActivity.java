package com.example.foodrecipiesbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    int[] images = {
            R.drawable.burgers,
            R.drawable.fajita,
            R.drawable.biryani,
            R.drawable.burgers,
            R.drawable.fajita,
            R.drawable.biryani,
    };

    String[] title = {
            "Chicken burger with extra cheese",
            "Fajita Burger with extra cheese",
            "White rice chicken biryani",
            "Chicken burger with extra cheese",
            "Fajita Burger with extra cheese",
            "White rice chicken biryani",
    };

    String[] ingredients = {
            "Ingredients: Item1, Item2, Item3, ...",
            "Ingredients: Item1, Item2, Item3, ...",
            "Ingredients: Item1, Item2, Item3, ...",
            "Ingredients: Item1, Item2, Item3, ...",
            "Ingredients: Item1, Item2, Item3, ...",
            "Ingredients: Item1, Item2, Item3, ...",
    };

    String[] time = {
            "20 MINT",
            "30 MINT",
            "120 MINT",
            "20 MINT",
            "30 MINT",
            "120 MINT",

    };


    private CardView fastFoodRecipesCardView;
    private CardView pakistaniRecipes;
    private CardView vegRecipes;
    private CardView chickenRecipes;


    private androidx.appcompat.widget.Toolbar myToolbar;

    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayout = findViewById(R.id.recommendedRecipesLayout);

        LayoutInflater layoutInflater = LayoutInflater.from(HomeActivity.this);

        myToolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Food Recipe Book");


        // Creating button for toolbar only

        ImageView toolbarButton = new ImageView(this);

        Toolbar.LayoutParams toolBarLayoutParam = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolBarLayoutParam.gravity = Gravity.END;
        toolBarLayoutParam.setMarginStart(300);
        toolbarButton.setEnabled(true);


        toolbarButton.setLayoutParams(toolBarLayoutParam);
        toolbarButton.setImageResource(R.drawable.login);
        myToolbar.addView(toolbarButton);


        fastFoodRecipesCardView = findViewById(R.id.fastFoodRecipes);
        pakistaniRecipes = findViewById(R.id.pakistaniRecipe);
        vegRecipes = findViewById(R.id.vegRecipes);
        chickenRecipes = findViewById(R.id.chickenRecipes);


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardToListNavigation(HomeActivity.this, RecipesListActivity.class);
            }
        };

        fastFoodRecipesCardView.setOnClickListener(onClickListener);
        pakistaniRecipes.setOnClickListener(onClickListener);
        vegRecipes.setOnClickListener(onClickListener);
        chickenRecipes.setOnClickListener(onClickListener);
        toolbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardToListNavigation(HomeActivity.this, LoginActivity.class);
                finish();
            }
        });


//        fastFoodRecipesCardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(HomeActivity.this, "I'm clicked", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(HomeActivity.this, RecipesListActivity.class);
////                startActivity(intent);
//
//                cardToListNavigation(HomeActivity.this);
//            }
//        });
    }

    private void cardToListNavigation(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        startActivity(intent);
    }
}