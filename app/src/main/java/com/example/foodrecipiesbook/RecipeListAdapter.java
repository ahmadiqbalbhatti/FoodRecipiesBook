package com.example.foodrecipiesbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeListAdapter extends BaseAdapter {

    private Context context;
    private int[] listOfImages;
    private String[] listOfTitle;
    private String[] listOfIngredients;
    private String[] listOfTime;

    private LayoutInflater layoutInflater;

    public RecipeListAdapter(Context context, int[] listOfImages, String[] listOfTitle, String[] listOfIngredients, String[] listOfTime) {
        this.context = context;
        this.listOfImages = listOfImages;
        this.listOfTitle = listOfTitle;
        this.listOfIngredients = listOfIngredients;
        this.listOfTime = listOfTime;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfTitle.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.recipe_card_design, null);

        ImageView recipeImage = convertView.findViewById(R.id.recipeImage);
        TextView recipeTitle = convertView.findViewById(R.id.recipeTitle);
        TextView recipeIngredients = convertView.findViewById(R.id.recipeIngredients);
        TextView recipeCookingTime = convertView.findViewById(R.id.recipeCookingTime);


        recipeImage.setImageResource(listOfImages[position]);
        recipeTitle.setText(listOfTitle[position]);
        recipeIngredients.setText(listOfIngredients[position]);
        recipeCookingTime.setText(listOfTime[position]);

        return convertView;
    }
}
