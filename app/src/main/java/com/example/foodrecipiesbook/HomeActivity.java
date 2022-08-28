package com.example.foodrecipiesbook;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.foodrecipiesbook.Adapters.RecipeListAdapter;
import com.example.foodrecipiesbook.CustomClasses.General;
import com.example.foodrecipiesbook.FireBase.DataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.LinkedList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    int[] images ={
            R.drawable.burgers,
            R.drawable.fajita,
            R.drawable.biryani,
            R.drawable.burgers,
            R.drawable.fajita,
            R.drawable.biryani,
    };

    String[] title={
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

    LinkedList<DataModel> dataModelLinkedList;


    public HomeActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        dialog = new Dialog(this);
        General generalFunctions = new General(this);
        dataModelLinkedList = new LinkedList<>();
        DataModel dataModel = new DataModel(R.drawable.biryani, "My Biryani",
                "120 MINT", "Item1, Item2, Item3, Item4, Item5");
        dataModelLinkedList.add(dataModel);


//
//        System.out.println("I'm groot I'm groot I'm groot I'm groot I'm groot" +
//                " I'm groot I'm groot I'm groot I'm groot I'm grootI'm groot " +
//                "I'm groot I'm groot    "+ dataModelLinkedList.get(0).title);


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

        fastFoodRecipesCardView.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        pakistaniRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        vegRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        chickenRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));


        addNewRecipeFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

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

//                String title = titleEditText.getText().toString();
                ListView newListView = findViewById(R.id.newRecipeListView);

//                System.out.println(" Im groot Im groot Im groot Im groot Im " +
//                        "groot Im groot Im groot Im groot Im groot Im groot" + dataModelLinkedList.get(0).title);
//                HomeListAdapter homeListAdapter =
//                        new HomeListAdapter(HomeActivity.this, dataModelLinkedList);

                RecipeListAdapter recipeListAdapter =
                        new RecipeListAdapter(HomeActivity.this, images,
                                title, ingredients, time);

                newListView.setAdapter(recipeListAdapter);

                Toast.makeText(HomeActivity.this, "I got pressed",
                        Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        cancelButton.setOnClickListener(v -> closeDialog());
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