package com.example.tmitchell.mypersonalcookbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class AddRecipe extends AppCompatActivity implements View.OnClickListener {

    /*********************************************
     * Global counter for adding new ingredients
     ********************************************/
    private int counter = 0;
    private LinearLayout ingredientLL;
    private ArrayList<EditText> ingredientList = new ArrayList<>();


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
        ingredientLL = (LinearLayout) findViewById(R.id.ingredients_ll);

        final ScrollView sv = (ScrollView) findViewById(R.id.addRecipe_ScrollView);
        final ImageButton addIngredientBtn = (ImageButton) findViewById(R.id.add_ingredient_button);
        final ImageButton removeIngredientBtn = (ImageButton) findViewById(R.id.remove_ingredient_button);

        Button saveButton = (Button) findViewById(R.id.addRecipe_submit_button);
        //CATEGORY DROPDOWN OPTIONS
        List<String> categories = new ArrayList<>();
        categories.add("Starter");
        categories.add("Main");
        categories.add("Dessert");
        categories.add("Snack");

        /*********************
         Add ingredient button
         ********************/
        assert addIngredientBtn != null;
        addIngredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientEdit();
            }
        });

        /*************************
         Remove ingredient button
         *************************/
        assert removeIngredientBtn != null;
        removeIngredientBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeIngredientEdit();
            }
        });

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
                                          //EditText ingredients_in = (EditText) findViewById(R.id.addRecipe_ingredientList_in);
                                          EditText directions_in = (EditText) findViewById(R.id.addRecipe_Directions_in);
                                          EditText comments_in = (EditText) findViewById(R.id.addRecipe_Comments_in);
                                          EditText prep_in = (EditText) findViewById(R.id.addRecipe_preptime_in);
                                          EditText cook_in = (EditText) findViewById(R.id.addRecipe_cooktime_in);


                                          @Override
                                          public void onClick(View v) {
                                              ArrayList<String> ingredients = new ArrayList<String>();

                                              /*************************************************
                                               Puts the ingredients values in to an array
                                               ************************************************/
                                              for (int i = 0; i < ingredientList.size(); i++) {
                                                  ingredients.add(ingredientList.get(i).getText().toString());
                                              }

                                              String ingredientStr = "";

                                              for (String s : ingredients) {
                                                  ingredientStr += s + ",";
                                              }


                                              //creates a validation object
                                              final Validation valid = new Validation();

                                              /*************************************************
                                               Sets the variables from text views on click
                                               ************************************************/
                                              //STRINGS
                                              String title = title_in.getText().toString();
                                              if (valid.nullCheck(title)) {
                                                  title_in.setError("Title must be given a value!");
                                              }

                                              String imgPath = "";
                                              String servesStr = serves_in.getText().toString();
                                              String catChoice = category.getSelectedItem().toString();
                                              //String ingredients = ingredients_in.getText().toString();
                                              String directions = directions_in.getText().toString();
                                              String comments = comments_in.getText().toString();
                                              String prepStr = prep_in.getText().toString();
                                              String cookStr = cook_in.getText().toString();

                                              //INTS
                                              final int servesNo = Integer.parseInt(servesStr);
                                              if (!valid.reasonableServes(servesNo)) {
                                                  serves_in.setError("Must serve at least one person, 20 maximum");
                                              }

                                              final int prepNo = Integer.parseInt(prepStr);
                                              if (!valid.reasonableTime(prepNo)) {
                                                  prep_in.setError("Does it really take more than 5 hours to prepare?");
                                              }

                                              final int cookNo = Integer.parseInt(cookStr);
                                              if (!valid.reasonableTime(cookNo)) {
                                                  cook_in.setError("Does it really take more than 5 hours to cook?");
                                              }

                                              /**
                                               * If the following validation returns true:
                                               * Title is not null.
                                               * Reasonable serve value.
                                               * Reasonable prep time.
                                               * Reasonable cook time.
                                               * */
                                              if (!valid.nullCheck(title) && valid.reasonableServes(servesNo)
                                                      && valid.reasonableTime(prepNo) && valid.reasonableTime(cookNo)) {

                                                  db.addRecipe(new RecipeDB(0, title, imgPath, catChoice, servesNo,
                                                          ingredientStr.toString(), directions, comments, prepNo, cookNo));

                                                  startActivity(new Intent(AddRecipe.this,
                                                          MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                              } else {
                                                  //if the validation fail send the user to the top of the activity
                                                  sv.fullScroll(ScrollView.FOCUS_UP);
                                              }
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


    //makes sure the user is directed to the main activity on back press
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(AddRecipe.this, MainActivity.class);
        startActivity(intent);
    }


    /**
     * CODE TO ADD DYNAMIC EDIT TEXT BOXES ADAPTED FROM:
     * http://findnerd.com/list/view/Create-EditText-on-button-click-in-Android/2723/
     * [ACCESSED 01/05/16]
     */
    //add a new edittext box in the view
    protected void addIngredientEdit() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

        counter++;

        params.setMargins(0, 10, 0, 10);
        EditText ingredient = new EditText(this);

        ingredient.setId(counter);
        ingredient.setInputType(InputType.TYPE_CLASS_TEXT);
        ingredient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        ingredient.setWidth(300);

        ingredientLL.addView(ingredient);
        ingredientList.add(ingredient);
    }

    //remove the last edittextbox from the view if one exists.
    protected void removeIngredientEdit() {

        if (counter > 0) {
            ingredientLL.removeViewAt(counter - 1);
            counter--;
        }

    }


}
