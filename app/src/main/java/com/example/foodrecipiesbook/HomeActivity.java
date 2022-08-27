package com.example.foodrecipiesbook;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodrecipiesbook.firebase.DataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    private Dialog dialog;
    private EditText titleEditText;
    private EditText durationEditText;
    private EditText ingredientsEditText;
    private EditText methodEditText;
    private Button saveRecipeButton;
    private Button cancelButton;
    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dialog = new Dialog(this);
        DataModel dataModel =  new DataModel();

        DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        LinearLayout linearLayout = findViewById(R.id.recommendedRecipesLayout);

        LayoutInflater layoutInflater = LayoutInflater.from(HomeActivity.this);

        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.myToolBar);

        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Food Recipe Book");

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        // Creating button for toolbar only
        ImageView toolbarButton = new ImageView(this);

        Toolbar.LayoutParams toolBarLayoutParam = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolBarLayoutParam.gravity = Gravity.END;
        toolBarLayoutParam.setMarginStart(300);
        toolbarButton.setEnabled(true);


        toolbarButton.setLayoutParams(toolBarLayoutParam);
        toolbarButton.setImageResource(R.drawable.login);
        myToolbar.addView(toolbarButton);


        CardView fastFoodRecipesCardView = findViewById(R.id.fastFoodRecipes);
        CardView pakistaniRecipes = findViewById(R.id.pakistaniRecipe);
        CardView vegRecipes = findViewById(R.id.vegRecipes);
        CardView chickenRecipes = findViewById(R.id.chickenRecipes);

        FloatingActionButton addNewRecipeFAButton = findViewById(R.id.addNewRecipeFAButton);
        durationEditText = findViewById(R.id.recipeDuration);
        titleEditText = findViewById(R.id.newRecipeTitle);
        ingredientsEditText = findViewById(R.id.newRecipeIngredients);
        methodEditText = findViewById(R.id.recipeMethod);
        saveRecipeButton = findViewById(R.id.saveRecipe);
        cancelButton = findViewById(R.id.cancelRecipe);

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
        toolbarButton.setOnClickListener(v -> {
            finish();
        });

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

                dialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDialog();
            }
        });
        dialog.show();
    }

    private void closeDialog() {
        dialog.dismiss();
    }
}