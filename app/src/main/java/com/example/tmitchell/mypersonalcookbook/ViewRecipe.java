package com.example.tmitchell.mypersonalcookbook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewRecipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /******************************
         * CREATE NEW DATABASE HANDLER
         ******************************/
        DatabaseHandler dh = new DatabaseHandler(this);
        LinearLayout ll = (LinearLayout) findViewById(R.id.viewRecipe_LinearLayout);


        TextView title = (TextView) findViewById(R.id.title_view);
        TextView category = (TextView) findViewById(R.id.category_view);
        TextView serves = (TextView) findViewById(R.id.serves_view);
        TextView ingredients = (TextView) findViewById(R.id.ingredients_view);
        TextView directions = (TextView) findViewById(R.id.directions_view);
        TextView comments = (TextView) findViewById(R.id.comments_view);
        TextView prepTime = (TextView) findViewById(R.id.prep_view);
        TextView cookTime = (TextView) findViewById(R.id.cook_view);

        System.out.println("TEST: " + serves);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int id = extra.getInt("id");
            RecipeDB r = new RecipeDB();
            r = dh.getRecipe(id);

            String test = serves.toString();
            String test2 = prepTime.toString();
            String test3 = cookTime.toString();

            System.out.println("TEST: " + test);

            title.setText(r.get_title());
            category.setText(r.get_category());
            serves.setText(test);
            ingredients.setText(r.get_ingredientList());
            directions.setText(r.get_directions());
            comments.setText(r.get_comments());
            prepTime.setText(test2);
            cookTime.setText(test3);



        } else {

            /***********************************************************
             * IF THERE IS NO ID FROM INTENT ADD THE FOLLOWING MESSAGE
             **********************************************************/

            final TextView error = new TextView(this);

            error.setText(R.string.noEntries);

            final LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            RelativeLayout.LayoutParams.WRAP_CONTENT);

            error.setTextSize(20);
            error.setLayoutParams(params);

            assert ll != null;
            ll.addView(error, params);
        }
    }
}