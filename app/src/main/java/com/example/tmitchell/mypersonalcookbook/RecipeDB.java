package com.example.tmitchell.mypersonalcookbook;
/**
 * Code for creating the SQL lite Database for storing recipes on the device.
 * Code adapted from:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 * [Accessed 04/04/2016]
 */


import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.util.ArrayList;

/**
 * Created by Tmitchell on 04/04/2016.
 */
public class RecipeDB {

    /**************************
        Private Variables
     **************************/
    private int _id;
    private String _title;
    private String _imagePath;
    private String _category;
    private int _serves;
    private String _ingredientList;
    private String _directions;
    private String _comments;
    private int _prepTime;
    private int _cookTime;


    public RecipeDB(){

    }

    //Constructor
    public RecipeDB(int id, String title, String image, String category, int serves,
                    String ingredientList, String directions, String comments,
                    int prepTime, int cookTime){
        this._id = id;
        this._title = title;
        this._imagePath = image;
        this._category = category;
        this._serves = serves;
        this._ingredientList = ingredientList;
        this._directions = directions;
        this._comments = comments;
        this._prepTime = prepTime;
        this._cookTime = cookTime;
    }


    /**************************
        GET AND SET METHODS
     **************************/

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }


    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_image() {
        return _imagePath;
    }

    public void set_image(String _imagePath) {
        this._imagePath = _imagePath;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }

    public int get_serves() {
        return _serves;
    }

    public void set_serves(int _serves) {
        this._serves = _serves;
    }

    public String get_ingredientList() {
        return _ingredientList;
    }

    public void set_ingredientList(String _ingredientList) {
        this._ingredientList = _ingredientList;
    }

    public String get_directions() {
        return _directions;
    }

    public void set_directions(String _directions) {
        this._directions = _directions;
    }

    public String get_comments() {
        return _comments;
    }

    public void set_comments(String _comments) {
        this._comments = _comments;
    }

    public int get_prepTime() {
        return _prepTime;
    }

    public void set_prepTime(int _prepTime) {
        this._prepTime = _prepTime;
    }

    public int get_cookTime() {
        return _cookTime;
    }

    public void set_cookTime(int _cookTime) {
        this._cookTime = _cookTime;
    }
}
