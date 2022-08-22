package com.example.foodrecipiesbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendedRecipeListAdapter extends BaseAdapter {
    private Context context;
    private int[] listOfImages;
    private String[] listOfTitle;

    private LayoutInflater layoutInflater;

    public RecommendedRecipeListAdapter(Context context, int[] listOfImages, String[] listOfTitle) {
        this.context = context;
        this.listOfImages = listOfImages;
        this.listOfTitle = listOfTitle;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return listOfImages.length;
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
        convertView = layoutInflater.inflate(R.layout.food_card_layout_for_favorite, null);

        TextView recipeTitle = convertView.findViewById(R.id.cardRecipeTitle);
        ImageView cardImage = convertView.findViewById(R.id.cardBottomImage);

        recipeTitle.setText(listOfTitle[position]);
        cardImage.setImageResource(listOfImages[position]);

        return convertView;
    }
}
