package com.example.tmitchell.mypersonalcookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_recipe);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final DatabaseHandler db = new DatabaseHandler(this);

        /*********************************************
         Variables for adding into the Database
         ********************************************/

        LinearLayout ll = (LinearLayout) findViewById(R.id.addRecipe_LinearLayout);
        ScrollView sv = (ScrollView) findViewById(R.id.addRecipe_ScrollView);

        Button saveButton = (Button) findViewById(R.id.addRecipe_submit_button);
        //CATEGORY DROPDOWN OPTIONS
        List<String> categories = new ArrayList<>();
        categories.add("Starter");
        categories.add("Main");
        categories.add("Dessert");
        categories.add("Snack");


        //ADAPTER FOR SPINNER
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, categories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner category = (Spinner) findViewById(R.id.addRecipe_category_in);
        category.setAdapter(categoryAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        assert saveButton != null;
        saveButton.setOnClickListener(new View.OnClickListener() {
                                          /************************
                                           * SETS THE TEXT VIEWS
                                           ************************/
                                          EditText title_in = (EditText) findViewById(R.id.addRecipe_title_in);
                                          EditText serves_in = (EditText) findViewById(R.id.addRecipe_serves_in);
                                          EditText ingredients_in = (EditText) findViewById(R.id.addRecipe_ingredientList_in);
                                          EditText directions_in = (EditText) findViewById(R.id.addRecipe_Directions_in);
                                          EditText comments_in = (EditText) findViewById(R.id.addRecipe_Comments_in);
                                          EditText prep_in = (EditText) findViewById(R.id.addRecipe_preptime_in);
                                          EditText cook_in = (EditText) findViewById(R.id.addRecipe_cooktime_in);


                                          @Override
                                          public void onClick(View v) {
                                              /*************************************************
                                               Sets the variables from text views on click
                                               ************************************************/
                                              //STRINGS
                                              String title = title_in.getText().toString();
                                              String imgPath = "";
                                              String servesStr = serves_in.getText().toString();
                                              String catChoice = category.getSelectedItem().toString();
                                              String ingredients = ingredients_in.getText().toString();
                                              String directions = directions_in.getText().toString();
                                              String comments = comments_in.getText().toString();
                                              String prepStr = prep_in.getText().toString();
                                              String cookStr = cook_in.getText().toString();

                                              //INTS
                                              final int servesNo = Integer.parseInt(servesStr);
                                              final int prepNo = Integer.parseInt(prepStr);
                                              final int cookNo = Integer.parseInt(cookStr);

                                              db.addRecipe(new RecipeDB(0, title, imgPath, catChoice, servesNo,
                                                      ingredients, directions, comments, prepNo, cookNo));

                                              startActivity(new Intent(AddRecipe.this,
                                                      MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                          }
                                      }
        );
    }

    @Override
    public void onClick(View v) {

    }

    /*Go back to main activity on button click*/
    public void submitRecipe(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
