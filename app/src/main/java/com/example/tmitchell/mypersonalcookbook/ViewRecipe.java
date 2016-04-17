package com.example.tmitchell.mypersonalcookbook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int id = extra.getInt("id");
            RecipeDB r;
            r = dh.getRecipe(id);

            String servesStr = String.valueOf(r.get_serves());
            String prepTimeStr = String.valueOf(r.get_prepTime());
            String cookTimeStr = String.valueOf(r.get_cookTime());

            assert title != null;
            title.setText(r.get_title());

            assert category != null;
            category.setText(r.get_category());

            assert serves != null;
            serves.setText(servesStr);

            assert ingredients != null;
            ingredients.setText(r.get_ingredientList());

            assert directions != null;
            directions.setText(r.get_directions());

            assert comments != null;
            comments.setText(r.get_comments());

            assert prepTime != null;
            prepTime.setText(prepTimeStr);

            assert cookTime != null;
            cookTime.setText(cookTimeStr);

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


    /*Allows a recipe to be deleted*/
    public void deleteRecipe(View view) {
        /**
         * CONFIRMATION BUTTON CODE ADAPTED FROM: http://www.androidhive.info/2011/09/
         * how-to-show-alert-dialog-in-android/
         * [ACCESSED 16/04/2016]
         */
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewRecipe.this);

        // Setting Dialog Title
        alertDialog.setTitle("Confirm Delete");

        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want remove this recipe?");

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseHandler dh = new DatabaseHandler(ViewRecipe.this);
                        Bundle extra = getIntent().getExtras();

                        if (extra != null) {

                            int id = extra.getInt("id");
                            RecipeDB r = new RecipeDB();
                            r = dh.getRecipe(id);

                            dh.deleteRecipe(r);
                        }

                        startActivity(new Intent(ViewRecipe.this,
                                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    }
                }

        );

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }
        );

        alertDialog.show();
    }

    public void editRecipe(View view) {
        /**
         * Starts the edit recipe activity and passes the
         * recipe ID with the intent.
         */

        Bundle extra = getIntent().getExtras();
        int id = extra.getInt("id");
        Intent intent = new Intent(view.getContext(), EditRecipe.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}