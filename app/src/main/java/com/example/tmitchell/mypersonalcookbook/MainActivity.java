package com.example.tmitchell.mypersonalcookbook;
/**
 * Authour: Tmitchell
 * Created: 04/04/2016.
 * PURPOSE: Main Class which handles the Main Activity
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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

        List<RecipeDB> recipeStore;


        recipeStore = db.getAllRecipes();
        ListAdapter adapter = new ListAdapter(this, R.layout.itemlistrow, recipeStore);

        //ArrayLists to hold the recipes of each category
        List<RecipeDB> mainsList = new ArrayList<>();
        List<RecipeDB> starterList = new ArrayList<>();
        List<RecipeDB> dessertList = new ArrayList<>();
        List<RecipeDB> snackList = new ArrayList<>();

        //Loops through the recipeStore array and breaks it
        //into the categories.
        for (int i = 0; i < recipeStore.size(); i++) {

            switch (recipeStore.get(i).get_category()) {

                case "Main":
                    mainsList.add(recipeStore.get(i));
                    break;
                case "Starter":
                    starterList.add(recipeStore.get(i));
                    break;
                case "Dessert":
                    dessertList.add(recipeStore.get(i));
                    break;
                case "Snack":
                    snackList.add(recipeStore.get(i));
                    break;
                default:
                    break;
            }
        }

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

            noEntries.setGravity(Gravity.CENTER);
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
