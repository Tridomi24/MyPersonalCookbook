package com.example.tmitchell.mypersonalcookbook;
/**
 * Code for creating the SQL lite Database for storing recipes on the device.
 * Code adapted from:
 * http://www.androidhive.info/2011/11/android-sqlite-database-tutorial/
 * [Accessed 04/04/2016]
 */


import android.graphics.Bitmap;

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
    private Bitmap _image;
    private String _category;
    private int _serves;
    private ArrayList _ingredientList;
    private String _directions;
    private String _comments;
    private int _prepTime;
    private int _cookTime;

    //Constructor
    public RecipeDB(int id, String title, Bitmap image, String category, int serves,
                    ArrayList ingredientList, String directions, String comments,
                    int prepTime, int cookTime){
        this._id = id;
        this._title = title;
        this._image = image;
        this._category = category;
        this._serves = serves;
        this._ingredientList = ingredientList;
        this._directions = directions;
        this._comments = comments;
        this._prepTime = prepTime;
        this._cookTime = cookTime;
    }

    /*******************
         GET METHODS
     *******************/
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

    public Bitmap get_image() {
        return _image;
    }

    public void set_image(Bitmap _image) {
        this._image = _image;
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

    /*******************
        SET METHODS
     *******************/

    public void set_serves(int _serves) {
        this._serves = _serves;
    }

    public ArrayList get_ingredientList() {
        return _ingredientList;
    }

    public void set_ingredientList(ArrayList _ingredientList) {
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
