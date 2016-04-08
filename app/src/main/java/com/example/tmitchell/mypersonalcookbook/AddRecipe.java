package com.example.tmitchell.mypersonalcookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class AddRecipe extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final DatabaseHandler db =  new DatabaseHandler(this);

        /*********************************************
            Variables for adding into the Database
         ********************************************/

        LinearLayout ll = (LinearLayout) findViewById(R.id.addRecipe_LinearLayout);
        ScrollView sv = (ScrollView) findViewById(R.id.addRecipe_ScrollView);

        Button saveButton = (Button) findViewById(R.id.addRecipe_submit_button);

        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
            /************************
             * SETS THE TEXT VIEWS
             ************************/
            EditText title_in = (EditText) findViewById(R.id.addRecipe_title_in);
            EditText category_in = (EditText) findViewById(R.id.addRecipe_category_in);
            EditText serves_in = (EditText) findViewById(R.id.addRecipe_serves_in);



            @Override
            public void onClick(View v) {
                /*************************************************
                 Sets the variables from text views on click
                 ************************************************/
                //STRINGS
                String title = title_in.getText().toString();
                String category = category_in.getText().toString();
                String servesStr = serves_in.getText().toString();

                //INTS
                final int servesNo = Integer.parseInt(servesStr);


                db.addRecipe(new RecipeDB(1, title, null, category, servesNo,
                        null, null, null, 0, 0));

                startActivity(new Intent(AddRecipe.this,
                       MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

    }

    @Override
    public void onClick(View v) {

    }

    /*Go back to main activity on button click*/
    public void submitRecipe(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
