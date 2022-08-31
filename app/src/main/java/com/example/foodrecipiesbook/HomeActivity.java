package com.example.foodrecipiesbook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipiesbook.Adapters.AdapterForRecommendedRecipes;
import com.example.foodrecipiesbook.Adapters.CustomRecyclerViewAdapter;
import com.example.foodrecipiesbook.CustomClasses.General;
import com.example.foodrecipiesbook.FireBase.DataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity{

    ArrayList<DataModel> dataModelArrayList;

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
    private RecyclerView recyclerView;
    private CardView fastFoodRecipesCardView;
    private CardView pakistaniRecipes;
    private CardView vegRecipes;
    private CardView chickenRecipes;

    public HomeActivity() {
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

        dialog = new Dialog(this);
        General generalFunctions = new General(this);

        dataModelArrayList = new ArrayList<>();

//        DataModel dataModel = new DataModel();
//        dataModel.image = R.drawable.biryani;
//        dataModel.title = "Chicken burger with extra cheese";
//        dataModel.ingredients = "Item1, Item2, Item3, Item4, Item5";
//        dataModel.duration = "50 MINT";
//
//        dataModelArrayList.add(dataModel);

//        HashMap<String,  Object> data = new HashMap<>();
//        data.put("image",R.drawable.desert);
//        data.put("title","My Lovey Sweet Dish");
//        data.put("ingredients","Item1, Item2, Item3, Item4");
//        data.put("duration", "12 MINT");
//        data.put("method", "It is a long established fact that a reader will " +
//                "be  distracted by the readable content of a page when " +
//                "looking at its layout content here', making it" +
//                " look like readable English.");
//        FirebaseFirestoreClass firebaseFirestoreClass =
//                new FirebaseFirestoreClass(HomeActivity.this);
//        firebaseFirestoreClass.addNewItem("recipe", data);

        androidx.appcompat.widget.Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

//        Objects.requireNonNull(getSupportActionBar()).setTitle("Hi, Ahmad!");

//        nav = (NavigationView) findViewById(R.id.navigationView);
//        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        toggle = new ActionBarDrawerToggle(HomeActivity.this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
//        drawerLayout.addDrawerListener(toggle);
//        nav.bringToFront();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        toggle.syncState();

        fastFoodRecipesCardView.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        pakistaniRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        vegRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        chickenRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

//        addRecommendedRecipesInView();

        addNewRecipeFAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
                addUserRecipesInRecyclerView();
            }
        });


    }

    private void addUserRecipesInRecyclerView() {
        CustomRecyclerViewAdapter customRecyclerViewAdapter =
                new CustomRecyclerViewAdapter(HomeActivity.this,
                        dataModelArrayList, R.layout.recipe_card_design);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(HomeActivity.this,
                        LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.newRecipeListView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(customRecyclerViewAdapter);

        TextView textView = findViewById(R.id.noRecipeAddedYet);
        if (customRecyclerViewAdapter.getItemCount() != 0) {
            textView.setVisibility(textView.GONE);
        }


    }

    private void addRecommendedRecipesInView() {
        AdapterForRecommendedRecipes adapterForRecommendedRecipes = new AdapterForRecommendedRecipes(HomeActivity.this,
                dataModelArrayList,
                R.layout.food_card_layout_for_recommended_recipes);

        LinearLayoutManager linearLayoutManager =
                new LinearLayoutManager(HomeActivity.this,
                        LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterForRecommendedRecipes);
    }


    private void openDialog() {
        dialog.setContentView(R.layout.add_new_recipe_layout_design);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        saveRecipeButton = dialog.findViewById(R.id.saveRecipe);
        cancelButton = dialog.findViewById(R.id.cancelRecipe);

        saveRecipeButton.setOnClickListener(closeDialogOnSave());
        cancelButton.setOnClickListener(v -> closeDialog());
        dialog.show();
    }

    private void closeDialog() {
        dialog.dismiss();
    }

    private View.OnClickListener closeDialogOnSave() {
        return v -> {
            titleEditText = dialog.findViewById(R.id.newRecipeTitle);
            durationEditText = dialog.findViewById(R.id.recipeDuration);
            ingredientsEditText = dialog.findViewById(R.id.newRecipeIngredients);
            methodEditText = dialog.findViewById(R.id.recipeMethod);

            DataModel dataModel = new DataModel();
            dataModel.image = R.drawable.your_recipe_default_pic;
            dataModel.title = titleEditText.getText().toString();
            dataModel.duration = durationEditText.getText().toString();
            dataModel.method = methodEditText.getText().toString();
            dataModel.ingredients =
                    ingredientsEditText.getText().toString();

            dataModelArrayList.add(dataModel);
            addUserRecipesInRecyclerView();
            dialog.dismiss();
        };
    }

    private void init() {
        addNewRecipeFAButton = findViewById(R.id.addNewRecipeFAButton);
        durationEditText = findViewById(R.id.recipeDuration);
        titleEditText = findViewById(R.id.newRecipeTitle);
        ingredientsEditText = findViewById(R.id.newRecipeIngredients);
        methodEditText = findViewById(R.id.recipeMethod);
        saveRecipeButton = findViewById(R.id.saveRecipe);
        cancelButton = findViewById(R.id.cancelRecipe);
        fastFoodRecipesCardView = findViewById(R.id.fastFoodRecipes);
        pakistaniRecipes = findViewById(R.id.pakistaniRecipe);
        vegRecipes = findViewById(R.id.vegRecipes);
        chickenRecipes = findViewById(R.id.chickenRecipes);
    }



}