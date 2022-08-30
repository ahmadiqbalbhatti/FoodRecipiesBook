package com.example.foodrecipiesbook.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodrecipiesbook.FireBase.DataModel;
import com.example.foodrecipiesbook.R;

import java.util.ArrayList;

public class AdapterForRecommendedRecipes extends RecyclerView.Adapter<AdapterForRecommendedRecipes.ViewHolder> {
    private final ArrayList<DataModel> dataModelArrayList;
    private Context context;
    private int viewId;

    public AdapterForRecommendedRecipes(Context context, ArrayList<DataModel> dataModelArrayList, int viewId) {
        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
        this.viewId = viewId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewId,
                parent, false);
        return new AdapterForRecommendedRecipes.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataModel dataModel = dataModelArrayList.get(position);
//        holder.recipeImage.setImageResource(dataModel.getImage());
//        holder.recipeTitle.setText(dataModel.getTitle());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView recipeImage;
        TextView recipeTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeImage = itemView.findViewById(R.id.recipeImage);
            recipeTitle = itemView.findViewById(R.id.recipeTitle);
        }
    }
}

