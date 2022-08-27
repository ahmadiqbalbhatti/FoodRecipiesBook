package com.example.foodrecipiesbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class RecipesListActivity extends AppCompatActivity {

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


    private ListView listView;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes_list);

        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Fast Food Recipes List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.recipeListView);
        listView.setAdapter(new RecipeListAdapter(getApplicationContext(), images, title, ingredients, time));




        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {



                Toast.makeText(RecipesListActivity.this, time[position], Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RecipesListActivity.this, RecipeDetailActivity.class);
                intent.putExtra("title", title[position]);
                intent.putExtra("ingredients", ingredients[position]);
                intent.putExtra("imageId", images[position]);
                intent.putExtra("time", time[position]);

                startActivityForResult(intent, 1);
            }
        });


    }
}