package com.example.foodrecipiesbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {


    private NavigationView nav;
    private ActionBarDrawerToggle toggle;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private Dialog dialog;
    private EditText titleEditText;
    private EditText durationEditText;
    private EditText ingredientsEditText;
    private EditText methodEditText;
    private Button saveRecipeButton;
    private Button cancelButton;
    private FloatingActionButton addNewRecipeFAButton;

    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dialog = new Dialog(this);

        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Hi, Ahmad!");

////        nav = (NavigationView) findViewById(R.id.navigationView);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(toggle);
////        nav.bringToFront();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        toggle.syncState();


        CardView fastFoodRecipesCardView = findViewById(R.id.fastFoodRecipes);
        CardView pakistaniRecipes = findViewById(R.id.pakistaniRecipe);
        CardView vegRecipes = findViewById(R.id.vegRecipes);
        CardView chickenRecipes = findViewById(R.id.chickenRecipes);

        init();

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


        addNewRecipeFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    private void cardToListNavigation(Context context, Class activity) {
        Intent intent = new Intent(context, activity);
        startActivity(intent);
    }


    private void openDialog() {
        dialog.setContentView(R.layout.add_new_recipe_layout_design);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        saveRecipeButton = dialog.findViewById(R.id.saveRecipe);
        cancelButton = dialog.findViewById(R.id.cancelRecipe);
        saveRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Save", Toast.LENGTH_SHORT).show();
                titleEditText = dialog.findViewById(R.id.newRecipeTitle);
                durationEditText = dialog.findViewById(R.id.recipeDuration);
                ingredientsEditText = dialog.findViewById(R.id.newRecipeIngredients);
                methodEditText = dialog.findViewById(R.id.recipeMethod);

                String title = titleEditText.getText().toString();

                Toast.makeText(HomeActivity.this, title, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void closeDialog() {
        dialog.dismiss();
    }

    private void init() {
        addNewRecipeFAButton = findViewById(R.id.addNewRecipeFAButton);
        durationEditText = findViewById(R.id.recipeDuration);
        titleEditText = findViewById(R.id.newRecipeTitle);
        ingredientsEditText = findViewById(R.id.newRecipeIngredients);
        methodEditText = findViewById(R.id.recipeMethod);
        saveRecipeButton = findViewById(R.id.saveRecipe);
        cancelButton = findViewById(R.id.cancelRecipe);
    }


}