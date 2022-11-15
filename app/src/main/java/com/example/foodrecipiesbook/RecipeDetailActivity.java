package com.example.foodrecipiesbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.foodrecipiesbook.FireBase.DataModel;
import com.example.foodrecipiesbook.FireBase.FirebaseFirestoreClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RecipeDetailActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;
    FloatingActionButton editRecipeFloatingActionButton,
            deleteRecipeFloatingActionButton;
    ImageView headerImage;
    TextView recipeTitle;
    TextView recipeCookingTime;
    TextView recipeIngredientsList;
    TextView recipeDetail;

    EditText titleET, durationET, ingredientsET, methodEt;
    //    Boolean isFavorite = true;
    private Dialog dialog;
    private Button saveRecipeButton;
    private Button cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Start Cooking");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        dataModelArrayList = new ArrayList<>();

        dialog = new Dialog(this);


//        LinearLayout linearLayout = findViewById(R.id.favIcon);
//        ImageView favImage = findViewById(R.id.favoriteImage);


        headerImage = findViewById(R.id.headerImage);
        recipeTitle = findViewById(R.id.recipeTitle);
        recipeCookingTime = findViewById(R.id.cookingTime);
        recipeIngredientsList = findViewById(R.id.ingredientsList);
        recipeDetail = findViewById(R.id.description);

        editRecipeFloatingActionButton = findViewById(R.id.editRecipeFAButton);
        deleteRecipeFloatingActionButton =
                findViewById(R.id.deleteRecipeFAButton);


        Intent intent = getIntent();
        if (intent.hasExtra("title") && intent.hasExtra("ingredients") &&
                intent.hasExtra("imageId") && intent.hasExtra("time")) {
            recipeTitle.setText(intent.getStringExtra("title"));
            recipeCookingTime.setText(intent.getStringExtra("time"));
            recipeIngredientsList.setText(intent.getStringExtra("ingredients"));

            headerImage.setImageResource(intent.getIntExtra("imageId", 700009));
            recipeDetail.setText(intent.getStringExtra("method"));
            
            
            editRecipeFloatingActionButton.setOnClickListener(v -> openDialog());
            deleteRecipeFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RecipeDetailActivity.this, "Im dleete", Toast.LENGTH_SHORT).show();
                    AlertDialog.Builder builder =
                            new AlertDialog.Builder(RecipeDetailActivity.this   );
                    builder.setTitle("Aleart!");
                    builder.setMessage("Do you want to remove this Recipe");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(RecipeDetailActivity.this, "Yess is pressed", Toast.LENGTH_SHORT).show();
                            FirebaseFirestoreClass firebaseFirestoreClass =
                                    new FirebaseFirestoreClass(RecipeDetailActivity.this );
                            firebaseFirestoreClass.filter("myRecipe", "title"
                                    , intent.getStringExtra("title")).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    for (DocumentSnapshot document :
                                            queryDocumentSnapshots) {
                                        firebaseFirestoreClass.Delete(
                                                "myRecipe",document.getId() );
                                        Toast.makeText(RecipeDetailActivity.this, intent.getStringExtra("title"), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(RecipeDetailActivity.this,
                                            e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(RecipeDetailActivity.this, "Deletion Cancel", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.create().show();

                }
            });
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

    private void openDialog() {
        dialog.setContentView(R.layout.add_new_recipe_layout_design);
        TextView dialogTitle = dialog.findViewById(R.id.dialogTitle);
        dialogTitle.setText("Update Recipe");
        titleET = dialog.findViewById(R.id.newRecipeTitle);
        durationET = dialog.findViewById(R.id.recipeDuration);
        ingredientsET = dialog.findViewById(R.id.newRecipeIngredients);
        methodEt = dialog.findViewById(R.id.recipeMethod);

        titleET.setText(recipeTitle.getText());
        durationET.setText(recipeCookingTime.getText());
        ingredientsET.setText(recipeIngredientsList.getText());
        methodEt.setText(recipeDetail.getText());


        saveRecipeButton = dialog.findViewById(R.id.saveRecipe);
        cancelButton = dialog.findViewById(R.id.cancelRecipe);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        saveRecipeButton.setOnClickListener(closeDialogOnSave());

        cancelButton.setOnClickListener(closeDialog());

        dialog.show();
    }

    private View.OnClickListener closeDialog() {
        return v -> dialog.dismiss();
    }


    private View.OnClickListener closeDialogOnSave() {
        return v -> {
            recipeTitle = dialog.findViewById(R.id.newRecipeTitle);
            recipeCookingTime = dialog.findViewById(R.id.recipeDuration);
            recipeIngredientsList = dialog.findViewById(R.id.newRecipeIngredients);
            recipeDetail = dialog.findViewById(R.id.recipeMethod);

            FirebaseFirestoreClass fireStoreDatabase =
                    new FirebaseFirestoreClass(RecipeDetailActivity.this);
            fireStoreDatabase.filter("myRecipe", "title",
                    recipeTitle.getText().toString()).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (DocumentSnapshot documentSnapshot :
                            queryDocumentSnapshots) {
                        if (Objects.equals(documentSnapshot.get("title"), recipeTitle.getText().toString())) {
                            HashMap<String, Object> recipe = new HashMap<>();
                            recipe.put("image", documentSnapshot.get("image").toString());
                            recipe.put("title", recipeTitle.getText().toString());
                            recipeTitle.setEnabled(false);
                            recipe.put("duration", recipeCookingTime.getText().toString());
                            recipe.put("ingredients",
                                    recipeIngredientsList.getText().toString());
                            recipe.put("method", recipeDetail.getText().toString());

                            Toast.makeText(RecipeDetailActivity.this,
                                    documentSnapshot.getId(),
                                    Toast.LENGTH_SHORT).show();
                            fireStoreDatabase.Update("recipe",
                                    documentSnapshot.getId(), recipe);
                            finish();
                        }
                    }
                }
            }).addOnFailureListener(e -> Toast.makeText(RecipeDetailActivity.this, e.getMessage(),
                    Toast.LENGTH_SHORT).show());

            dialog.dismiss();
        };
    }
}