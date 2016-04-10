package com.example.tmitchell.mypersonalcookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ViewRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

}
