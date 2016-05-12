package com.example.tmitchell.mypersonalcookbook;
/**
 * Author: Tmitchell
 * Created: 06/04/2016.
 * Purpose: contains methods that are used in the ViewRecipe Activity
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ViewRecipe extends AppCompatActivity {

    private LinearLayout ingredientLL;

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
        ingredientLL = (LinearLayout) findViewById(R.id.ingredients_view);


        TextView title = (TextView) findViewById(R.id.title_view);
        TextView category = (TextView) findViewById(R.id.category_view);
        TextView serves = (TextView) findViewById(R.id.serves_view);
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

            /***********************************
             * DISPLAYING THE STORED INGREDIENTS
             **********************************/

            String ingredientsTmp = String.valueOf(r.get_ingredientList());
            String ingtmp = ingredientsTmp.replace("\"", "");

            String[] ingStore = ingtmp.split(",");

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            params.setMargins(0, 10, 0, 10);


            for (int i = 0; i < ingStore.length; i++) {
                TextView ingredient = new TextView(this);

                ingredient.setId(i);
                ingredient.setInputType(InputType.TYPE_CLASS_TEXT);
                ingredient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                ingredient.setTextColor(Color.BLACK);
                ingredient.setPadding(25, 0, 0, 0);
                ingredient.setText(ingStore[i]);

                ingredientLL.addView(ingredient);
            }

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

    //makes sure the user is directed to the main activity on back press
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(ViewRecipe.this, MainActivity.class);
        startActivity(intent);
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
                            RecipeDB r;
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