package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.foodrecipiesbook.Adapters.RecipeListAdapter;
import com.example.foodrecipiesbook.FireBase.DataModel;
import com.example.foodrecipiesbook.FireBase.FirebaseFirestoreClass;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class RecipesListActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;
    FirebaseFirestoreClass firebaseFirestoreClass;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        firebaseFirestoreClass =
                new FirebaseFirestoreClass(getApplicationContext());
        dataModelArrayList = new ArrayList<>();

        listView = findViewById(R.id.recipeListView);
        Toast.makeText(RecipesListActivity.this, "Hi there", Toast.LENGTH_SHORT).show();

        readRecipeCollection("recipe");
        readRecipeCollection("myRecipe");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RecipesListActivity.this, RecipeDetailActivity.class);

                intent.putExtra("title",
                        dataModelArrayList.get(position).title);
                intent.putExtra("ingredients",
                        dataModelArrayList.get(position).ingredients);
                intent.putExtra("imageId",
                        dataModelArrayList.get(position).image);
                intent.putExtra("time",
                        dataModelArrayList.get(position).duration);
                intent.putExtra("method",
                        dataModelArrayList.get(position).method);

                startActivityForResult(intent, 1);
            }
        });
    }

    private void readRecipeCollection(String collection){
        firebaseFirestoreClass.readItem(collection).addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
        @Override
        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            for (DocumentSnapshot documentSnapshot :
                    queryDocumentSnapshots) {
                if (!queryDocumentSnapshots.isEmpty()){
                    DataModel dataModel = new DataModel();
                    dataModel.title =
                            documentSnapshot.get("title").toString();
                    dataModel.duration = documentSnapshot.get("duration").toString() ;
                    dataModel.method =
                            documentSnapshot.get("method").toString();
                    dataModel.ingredients = documentSnapshot.get(
                            "ingredients").toString();
                    dataModel.image =
                            Integer.parseInt(documentSnapshot.get("image").toString());

                    dataModelArrayList.add(dataModel);
//                        dataModelArrayList.add(documentSnapshot.toObject(DataModel.class));
                    listView.setAdapter(new RecipeListAdapter(getApplicationContext(),
                            dataModelArrayList, R.layout.recipe_card_design));
                }

            }
        }
    });}

}