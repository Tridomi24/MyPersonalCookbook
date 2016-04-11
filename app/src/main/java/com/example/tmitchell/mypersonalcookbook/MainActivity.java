package com.example.tmitchell.mypersonalcookbook;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);

        /*****************
         * CRUD Operations
         ****************/
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainLayout);
        Button addRecipe_btn = (Button) findViewById(R.id.addRecipe_Button);
        TextView recipeCount = (TextView) findViewById(R.id.numberOfRecipes);
        ListView recipeList = (ListView) findViewById(R.id.storedRecipesList);

        List<RecipeDB> recipeStore = new ArrayList<RecipeDB>();

        recipeStore = db.getAllRecipes();
        ListAdapter adapter = new ListAdapter(this, R.layout.itemlistrow, recipeStore);

        assert recipeList != null;
        recipeList.setAdapter(adapter);
        recipeList.setOnItemClickListener(new ListClickHandler());

        int count;
        count = db.recipeCount();

        assert recipeCount != null;
        recipeCount.setText("Total Recipes: " + count);


            //Creates a new text view if the database is empty
            if (count == 0) {
                final TextView noEntries = new TextView(this);

                noEntries.setText(R.string.noEntries);

                final RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.numberOfRecipes);
                noEntries.setTextSize(20);
                noEntries.setLayoutParams(params);

                assert rl != null;
                rl.addView(noEntries, params);
            }

        }


    /*Start the add Recipe Activity*/
    public void addRecipe(View view) {
        Intent intent = new Intent(this, AddRecipe.class);
        startActivity(intent);
    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
