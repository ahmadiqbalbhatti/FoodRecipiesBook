package com.example.foodrecipiesbook.CustomClasses;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class General {

    private Context context;

    public General(Context context) {
        this.context = context;
    }

    public void simpleNavigationIntent(Class activity) {
        Intent intent = new Intent(context, activity);
        context.startActivity(intent);
    }

    public View.OnClickListener simpleClickListener(Class activity){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleNavigationIntent(activity);
            }
        };
    }
}
