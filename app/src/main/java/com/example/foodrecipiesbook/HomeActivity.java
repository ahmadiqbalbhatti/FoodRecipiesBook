package com.example.foodrecipiesbook;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.foodrecipiesbook.FireBase.FirebaseFirestoreClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity{

    ArrayList<DataModel> dataModelArrayList;

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
    
    FirebaseFirestoreClass firebaseFirestoreClass;

    ActionBarDrawerToggle toggle;


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
        firebaseFirestoreClass =  new FirebaseFirestoreClass(HomeActivity.this);


        DrawerLayout dl = findViewById(R.id.drawerLayout);
        NavigationView nav = findViewById(R.id.navigationView);
        Toolbar t = findViewById(R.id.toolbar);
        setSupportActionBar(t);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toggle = new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);
        toggle.setDrawerIndicatorEnabled(true);
        dl.addDrawerListener(toggle);
        toggle.syncState();


        fastFoodRecipesCardView.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        pakistaniRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        vegRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

        chickenRecipes.setOnClickListener(generalFunctions.
                simpleClickListener(RecipesListActivity.class));

            firebaseFirestoreClass.readItem("myRecipe").addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot document :
                            queryDocumentSnapshots) {
                        DataModel dataModel = new DataModel();
                        dataModel.title =
                                Objects.requireNonNull(document.get("title")).toString();
                        dataModel.duration = Objects.requireNonNull(document.get("duration")).toString() ;
                        dataModel.method =
                                Objects.requireNonNull(document.get("method")).toString();
                        dataModel.ingredients = Objects.requireNonNull(document.get(
                                "ingredients")).toString();
                        dataModel.image =
                                Integer.parseInt(Objects.requireNonNull(document.get("image")).toString());
                    dataModelArrayList.add(dataModel);
                    addUserRecipesInRecyclerView();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

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
                        LinearLayoutManager.HORIZONTAL, false);
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

            if (!TextUtils.isEmpty(titleEditText.getText().toString()) &&
                    !TextUtils.isEmpty(durationEditText.getText().toString()) &&
                    !TextUtils.isEmpty(methodEditText.getText().toString()) &&
                    !TextUtils.isEmpty(ingredientsEditText.getText().toString())){
                dataModelArrayList.add(dataModel);
                addUserRecipesInRecyclerView();

                FirebaseFirestoreClass fireStoreDatabase =
                        new FirebaseFirestoreClass(HomeActivity.this    );

                HashMap<String, Object> recipe = new HashMap<>();
                recipe.put("image", R.drawable.your_recipe_default_pic);
                recipe.put("title", titleEditText.getText().toString());
                recipe.put("duration", durationEditText.getText().toString());
                recipe.put("ingredients", ingredientsEditText.getText().toString());
                recipe.put("method", methodEditText.getText().toString());
                fireStoreDatabase.addNewItem("myRecipe", recipe);
            }
            else{
                Toast.makeText(this, "Please! Fill Up every filed", Toast.LENGTH_SHORT).show();
            }
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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}