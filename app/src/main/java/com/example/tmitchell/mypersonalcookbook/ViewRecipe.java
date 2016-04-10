package com.example.tmitchell.mypersonalcookbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.sql.SQLOutput;

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
        TextView categoty = (TextView) findViewById(R.id.category_view);

        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            int id = extra.getInt("id");
            RecipeDB r = new RecipeDB();
            r = dh.getRecipe(id);

            title.setText(r.get_title());

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
