package com.example.foodrecipiesbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class RecommendedRecipeListAdapter extends BaseAdapter {
    private Context context;
    private int[] listOfImages;
    private String[] listOfTitle;

    private LayoutInflater layoutInflater;




    @Override
    public int getCount() {
        return 0;
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
        return null;
    }
}
