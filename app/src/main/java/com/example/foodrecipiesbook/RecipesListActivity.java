package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.foodrecipiesbook.Adapters.RecipeListAdapter;
import com.example.foodrecipiesbook.FireBase.DataModel;

import java.util.ArrayList;

public class RecipesListActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModelArrayList;

//        int[] images ={
//            R.drawable.burgers,
//            R.drawable.fajita,
//            R.drawable.biryani,
//            R.drawable.burgers,
//            R.drawable.fajita,
//            R.drawable.biryani,
//    };
//
//    String[] title={
//            "Chicken burger with extra cheese",
//            "Fajita Burger with extra cheese",
//            "White rice chicken biryani",
//            "Chicken burger with extra cheese",
//            "Fajita Burger with extra cheese",
//            "White rice chicken biryani",
//    };
//
//    String[] ingredients = {
//            "Ingredients: Item1, Item2, Item3, ...",
//            "Ingredients: Item1, Item2, Item3, ...",
//            "Ingredients: Item1, Item2, Item3, ...",
//            "Ingredients: Item1, Item2, Item3, ...",
//            "Ingredients: Item1, Item2, Item3, ...",
//            "Ingredients: Item1, Item2, Item3, ...",
//    };
//
//    String[] time = {
//            "20 MINT",
//            "30 MINT",
//            "120 MINT",
//            "20 MINT",
//            "30 MINT",
//            "120 MINT",
//
//    };


    private ListView listView;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dataModelArrayList = new ArrayList<>();

        DataModel dataModel = new DataModel();
        dataModel.image = R.drawable.biryani;
        dataModel.title = "Chicken burger with extra cheese";
        dataModel.ingredients = "Item1, Item2, Item3, Item4, Item5";
        dataModel.duration = "50 MINT";

        dataModelArrayList.add(dataModel);
//        getSupportActionBar().setTitle("Fast Food Recipes List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.recipeListView);
        listView.setAdapter(new RecipeListAdapter(getApplicationContext(),
                dataModelArrayList, R.layout.recipe_card_design));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



//                Toast.makeText(RecipesListActivity.this, time[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecipesListActivity.this, RecipeDetailActivity.class);
                intent.putExtra("title", dataModelArrayList.get(position).title);
                intent.putExtra("ingredients",
                        dataModelArrayList.get(position).ingredients);
                intent.putExtra("imageId", dataModelArrayList.get(position).image);
                intent.putExtra("time", dataModelArrayList.get(position).duration);

                startActivityForResult(intent, 1);
            }
        });


    }
}