package com.example.tmitchell.mypersonalcookbook;
/**
 * Code for creating the SQL lite Database for storing recipes on the device.
 * Code adapted from:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 * [Accessed 04/04/2016]
 */


import java.util.ArrayList;
import java.util.AbstractList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tmitchell on 04/04/2016.
 */
public class DatabaseHandler  extends SQLiteOpenHelper{

    /**************************
        All Static variables
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
         Creating the Database
     ****************************/

    public DatabaseHandler( Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /****************************
        Creating the Tables
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


}
