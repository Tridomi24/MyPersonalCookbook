package com.example.tmitchell.mypersonalcookbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db =  new DatabaseHandler(this);

        /**
         * CRUD Operations
         * */
        //insert recipe
        Log.d("Insert: ", "Inserting ...");
        db.addRecipe(new RecipeDB(1, "Salad", "filepath", "main", 2, "tomatoes, feta, lettuce",
                "Chop lettuce and tomatoes, add to bowl", "Dress with olive oil", 5, 0));
        db.addRecipe(new RecipeDB(2,"Cous-Cous", "filepath", "main", 1, "Cous-Cous Packet",
                "Put cous-cous in bowl, add boiling water", "season to taste", 0,5));


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.mainLayout);
        TextView recipeID = (TextView) findViewById(R.id.recipeID);
        TextView recipeTitle = (TextView) findViewById(R.id.recipeTitle);
        TextView recipeCategory = (TextView) findViewById(R.id.recipeCategory);
        TextView recipeServes = (TextView) findViewById(R.id.recipeServes);
        TextView recipeIngredients = (TextView) findViewById(R.id.recipeIngredients);
        TextView recipeDirections = (TextView) findViewById(R.id.recipeDirections);
        TextView recipeComments = (TextView) findViewById(R.id.recipeComments);

        recipeID.setText(Integer.toString(db.getRecipe(1).get_id()));
        recipeTitle.setText(db.getRecipe(1).get_title());
        recipeCategory.setText(db.getRecipe(1).get_category());
        recipeServes.setText(Integer.toString(db.getRecipe(1).get_serves()));
        recipeIngredients.setText(db.getRecipe(1).get_ingredientList());
        recipeDirections.setText(db.getRecipe(1).get_directions());
        recipeComments.setText(db.getRecipe(1).get_comments());


    }
}
