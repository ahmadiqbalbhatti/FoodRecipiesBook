package com.example.foodrecipiesbook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodrecipiesbook.FireBase.DataModel;
import com.example.foodrecipiesbook.R;

import java.util.ArrayList;

public class RecipeListAdapter extends BaseAdapter {

    private Context context;
    ArrayList<DataModel> dataModelArrayList;
    private int viewId;


    private LayoutInflater layoutInflater;

    public RecipeListAdapter(Context context, ArrayList<DataModel> dataModelArrayList, int viewId) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
        layoutInflater = LayoutInflater.from(context);
        this.viewId = viewId;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(viewId, null);

        ImageView recipeImage = convertView.findViewById(R.id.recipeImage);
        TextView recipeTitle = convertView.findViewById(R.id.recipeTitle);
        TextView recipeIngredients = convertView.findViewById(R.id.recipeIngredients);
        TextView recipeCookingTime = convertView.findViewById(R.id.recipeCookingTime);


        recipeImage.setImageResource(dataModelArrayList.get(position).image);
        recipeTitle.setText(dataModelArrayList.get(position).title);
        recipeIngredients.setText(dataModelArrayList.get(position).ingredients);
        recipeCookingTime.setText(dataModelArrayList.get(position).duration);

        return convertView;
    }
}
