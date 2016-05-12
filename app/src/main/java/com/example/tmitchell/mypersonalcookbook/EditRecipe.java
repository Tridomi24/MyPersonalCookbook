package com.example.tmitchell.mypersonalcookbook;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
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
import java.util.Arrays;
import java.util.List;

public class EditRecipe extends AppCompatActivity {

    private LinearLayout ingredientLL;
    private int counter = 0;

    private ArrayList<EditText> ingredientList = new ArrayList<>();

    String[] ingStore;
    EditText ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recipe);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /**
         * code to stop keyboard from automatically popping up taken from:
         * http://stackoverflow.com/questions/2496901/android-on-screen-keyboard-auto-popping-up
         * Accessed: 05/05/2016
         */
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        final ScrollView sv = (ScrollView) findViewById(R.id.editRecipe_ScrollView);
        ingredientLL = (LinearLayout) findViewById(R.id.ingredients_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        /******************************
         * CREATE NEW DATABASE HANDLER
         ******************************/
        final DatabaseHandler dh = new DatabaseHandler(this);

        /**********************
         * SET THE EDIT TEXTS
         *********************/
        EditText title = (EditText) findViewById(R.id.editRecipe_title_in);
        final Spinner category = (Spinner) findViewById(R.id.editRecipe_category_in);
        final EditText serves = (EditText) findViewById(R.id.editRecipe_serves_in);
        EditText directions = (EditText) findViewById(R.id.editRecipe_Directions_in);
        EditText comments = (EditText) findViewById(R.id.editRecipe_Comments_in);
        EditText prepTime = (EditText) findViewById(R.id.editRecipe_preptime_in);
        EditText cookTime = (EditText) findViewById(R.id.editRecipe_cooktime_in);

        Button updateButton = (Button) findViewById(R.id.editRecipe_submit_button);
        final ImageButton addIngredientBtn = (ImageButton) findViewById(R.id.add_ingredient_button_edit);
        final ImageButton removeIngredientBtn = (ImageButton) findViewById(R.id.remove_ingredient_button_edit);

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

        category.setAdapter(categoryAdapter);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.v("item", (String) parent.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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


        /**********************************
         * Pull the data from the Database
         *********************************/
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
            switch (r.get_category()) {
                case "Starter":
                    category.setSelection(0);
                    break;
                case "Main":
                    category.setSelection(1);
                    break;
                case "Dessert":
                    category.setSelection(2);
                    break;
                case "Snack":
                    category.setSelection(3);
            }

            assert serves != null;
            serves.setText(servesStr);


            /***********************************
             * DISPLAYING THE STORED INGREDIENTS
             **********************************/
            String ingredientsTmp = String.valueOf(r.get_ingredientList());
            String ingtmp = ingredientsTmp.replace("\"", "");

            ingStore = ingtmp.split(",");

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);

            params.setMargins(0, 10, 0, 10);


            for (int i = 0; i < ingStore.length; i++) {
                EditText ingredient = new EditText(this);

                ingredient.setId(i);
                ingredient.setInputType(InputType.TYPE_CLASS_TEXT);
                ingredient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
                ingredient.setTextColor(Color.BLACK);
                ingredient.setPadding(25, 0, 0, 0);
                ingredient.setText(ingStore[i]);

                ingredientLL.addView(ingredient);
            }
            counter = ingStore.length;

            assert directions != null;
            directions.setText(r.get_directions());

            assert comments != null;
            comments.setText(r.get_comments());

            assert prepTime != null;
            prepTime.setText(prepTimeStr);

            assert cookTime != null;
            cookTime.setText(cookTimeStr);
        }

        assert updateButton != null;
        updateButton.setOnClickListener(new View.OnClickListener() {
                                            /************************
                                             * SETS THE TEXT VIEWS
                                             ************************/
                                            EditText title_in = (EditText) findViewById(R.id.editRecipe_title_in);
                                            EditText serves_in = (EditText) findViewById(R.id.editRecipe_serves_in);
                                            EditText directions_in = (EditText) findViewById(R.id.editRecipe_Directions_in);
                                            EditText comments_in = (EditText) findViewById(R.id.editRecipe_Comments_in);
                                            EditText prep_in = (EditText) findViewById(R.id.editRecipe_preptime_in);
                                            EditText cook_in = (EditText) findViewById(R.id.editRecipe_cooktime_in);

                                            @Override
                                            public void onClick(View v) {
                                                Validation valid = new Validation();

                                                ArrayList<String> ingredients = new ArrayList<String>();

                                                /*************************************************
                                                 Puts the ingredients values in to an array
                                                 ************************************************/
                                                for (int i = 0; i < ingStore.length; i++) {
                                                    ingredients.add(ingStore[i]);

                                                    System.out.println("TEST" + i + ": " + ingStore[i]);
                                                }

                                                for (int i = 0; i < ingredientList.size(); i++) {
                                                    ingredients.add(ingredientList.get(i).getText().toString());
                                                    System.out.println("TEST" + i + ": " + ingredientList.get(i).getText().toString());
                                                }

                                                String ingredientStr = "";

                                                for (String s : ingredients) {
                                                    ingredientStr += s + ",";
                                                }

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
                                                    Bundle extra = getIntent().getExtras();
                                                    if (extra != null) {
                                                        int id = extra.getInt("id");
                                                        RecipeDB r;
                                                        r = dh.getRecipe(id);

                                                        r.set_title(title);
                                                        r.set_image(imgPath);
                                                        r.set_serves(servesNo);
                                                        r.set_category(catChoice);
                                                        r.set_ingredientList(ingredientStr);
                                                        r.set_directions(directions);
                                                        r.set_comments(comments);
                                                        r.set_prepTime(prepNo);
                                                        r.set_cookTime(cookNo);

                                                        dh.updateRecipe(r);

                                                    }
                                                    //Go back to the recipe view page, passes the ID with the intent
                                                    int id = extra.getInt("id");
                                                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                                                    intent.putExtra("id", id);
                                                    startActivity(intent);
                                                } else {
                                                    //if the validation fail send the user to the top of the activity
                                                    sv.fullScroll(ScrollView.FOCUS_UP);
                                                }
                                            }
                                        }
        );


    }

    //makes sure the user is directed to the main activity on back press
    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(EditRecipe.this, MainActivity.class);
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

        ingredient = new EditText(this);

        params.setMargins(0, 10, 0, 10);


        ingredient.setId(counter);
        ingredient.setInputType(InputType.TYPE_CLASS_TEXT);
        ingredient.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        ingredient.setWidth(300);

        ingredientLL.addView(ingredient);
        ingredientList.add(ingredient);

        counter++;
    }

    //remove the last edittextbox from the view if one exists.
    protected void removeIngredientEdit() {

        ingredient = new EditText(this);

        if (counter > 0) {
            ingredientLL.removeViewAt(counter - 1);

            if (ingredient.getText().toString().trim().length() > 0) {
                //removes the last ingredient from the array
                ingStore = Arrays.copyOf(ingStore, ingStore.length - 1);
            }

            counter--;
        }

    }


}
