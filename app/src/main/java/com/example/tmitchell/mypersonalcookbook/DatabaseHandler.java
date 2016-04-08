package com.example.tmitchell.mypersonalcookbook;
/**
 * Code for creating the SQL lite Database for storing recipes on the device.
 * Code adapted from:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 * [Accessed 04/04/2016]
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tmitchell on 04/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    /**************************
     * All Static variables
     **************************/

    //DB Version
    private static final int DATABASE_VERSION = 1;

    //Recipe Database name
    private static final String DATABASE_NAME = "recipeStorage";

    //Recipe table name
    private static final String TABLE_RECIPE = "recipe";

    //Column names for the recipe database
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_SERVES = "serves";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_DIRECTIONS = "directions";

    private static final String KEY_COMMENTS = "Comments";
    private static final String KEY_PREP_TIME = "prepTime";
    private static final String KEY_COOK_TIME = "cookTime";

    /****************************
     * Creating the Database
     ****************************/

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /****************************
     * Creating the Tables
     ****************************/

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECIPE_TABLE = "CREATE TABLE " + TABLE_RECIPE + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT," + KEY_IMAGE +
                " BLOB," + KEY_CATEGORY + " TEXT," + KEY_SERVES + " INTEGER," + KEY_INGREDIENTS +
                " TEXT," + KEY_DIRECTIONS + " TEXT," + KEY_COMMENTS + " TEXT," + KEY_PREP_TIME +
                " INTEGER," + KEY_COOK_TIME + " INTEGER" + ")";
        db.execSQL(CREATE_RECIPE_TABLE);
    }

    /****************************
         Upgrading the Database
     ****************************/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPE);

        //re-create the tables
        onCreate(db);
    }


    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    /*********************
        ADD NEW RECIPE
     ********************/
    void addRecipe(RecipeDB recipe) {
        SQLiteDatabase db = this.getWritableDatabase();

        //converts Ingredients ArrayList to a JSON that can be stored
        String ingredients = new Gson().toJson(recipe.get_ingredientList());

        //converts image to array that can be stored



        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, recipe.get_title()); // Recipe Title
        values.put(KEY_IMAGE, recipe.get_image()); // Recipe Image
        values.put(KEY_CATEGORY, recipe.get_category()); // Recipe category
        values.put(KEY_SERVES, recipe.get_serves()); // Recipe Serves
        values.put(KEY_INGREDIENTS, ingredients);
        values.put(KEY_DIRECTIONS, recipe.get_directions());
        values.put(KEY_COMMENTS, recipe.get_prepTime());
        values.put(KEY_PREP_TIME, recipe.get_prepTime());
        values.put(KEY_COOK_TIME, recipe.get_cookTime());

        // Inserting Row
        db.insert(TABLE_RECIPE, null, values);
        db.close(); // Closing database connection
    }

    /************************
        GET SINGLE RECIPE
     ***********************/
    RecipeDB getRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_RECIPE, new String[]{KEY_ID, KEY_TITLE, KEY_IMAGE,
                        KEY_CATEGORY, KEY_SERVES, KEY_INGREDIENTS,
                        KEY_DIRECTIONS, KEY_COMMENTS, KEY_PREP_TIME, KEY_COOK_TIME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        RecipeDB recipe = new RecipeDB(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getInt(8), cursor.getInt(9));

        return recipe;
    }

    /*********************
        GET ALL RECIPES
     ********************/
    //Get all stored recipes
    public List<RecipeDB> getAllRecipes() {
        List<RecipeDB> recipeList = new ArrayList<RecipeDB>();

        //SELECT all Query
        String selectQuery = "SELECT * FROM " + TABLE_RECIPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                RecipeDB recipe = new RecipeDB();

                recipe.set_id(Integer.parseInt(cursor.getString(0)));
                recipe.set_title((cursor.getString(1)));
                recipe.set_image(cursor.getString(2));
                recipe.set_category(cursor.getString(3));
                recipe.set_serves(Integer.parseInt(cursor.getString(4)));
                recipe.set_ingredientList(cursor.getString(5));
                recipe.set_directions(cursor.getString(6));
                recipe.set_comments(cursor.getString(7));
                recipe.set_prepTime(Integer.parseInt(cursor.getString(8)));
                recipe.set_cookTime(Integer.parseInt(cursor.getString(9)));

                //Add to the recipe List
                recipeList.add(recipe);
            } while (cursor.moveToNext());


        }
        return recipeList;
    }

    /**************************
     GET ALL ID's AND TITLES
     *************************/
    //Get all ID's and Titles of recipes
    public List<RecipeDB> getAll_ID_Title() {
        List<RecipeDB> recipeList = new ArrayList<RecipeDB>();

        //SELECT all Query
        String selectQuery = "SELECT " + KEY_ID + ",  " + KEY_TITLE + ", " + KEY_CATEGORY +
                " FROM " + TABLE_RECIPE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //loop through all rows and add to list
        if (cursor.moveToFirst()) {
            do {
                RecipeDB recipe = new RecipeDB();

                recipe.set_id(Integer.parseInt(cursor.getString(0)));
                recipe.set_title((cursor.getString(1)));

                //Add to the recipe List
                recipeList.add(recipe);
            } while (cursor.moveToNext());
        }
        return recipeList;
    }



    /*******************************
        UPDATING A SINGLE RECIPE
     ******************************/

    public int updateRecipe(RecipeDB recipe){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, recipe.get_title()); // Recipe Title
        values.put(KEY_IMAGE, recipe.get_image()); // Recipe Image
        values.put(KEY_CATEGORY, recipe.get_category()); // Recipe category
        values.put(KEY_SERVES, recipe.get_serves()); // Recipe Serves
        values.put(KEY_INGREDIENTS, recipe.get_ingredientList());
        values.put(KEY_DIRECTIONS, recipe.get_directions());
        values.put(KEY_COMMENTS, recipe.get_prepTime());
        values.put(KEY_PREP_TIME, recipe.get_prepTime());
        values.put(KEY_COOK_TIME, recipe.get_cookTime());

        //updating row
        return db.update(TABLE_RECIPE, values, KEY_ID + " = ?",
                new String[] { String.valueOf(recipe.get_id())});
    }

    /*******************************
        DELETING A SINGLE RECIPE
     ******************************/
    public void deleteRecipe(RecipeDB recipe){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECIPE, KEY_ID + " = ?", new String[]{String.valueOf(recipe.get_id())});
        db.close();
    }

    public int recipeCount(){
        String countQuery = "SELECT id FROM " + TABLE_RECIPE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(countQuery, null);

        c.moveToFirst();
        int count = c.getCount();
        c.close();

        return count;
    }
}
