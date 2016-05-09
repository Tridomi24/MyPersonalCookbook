package com.example.tmitchell.mypersonalcookbook;

/**
 * Created by Tmitchell on 01/05/2016.
 * Purpose: contains validation methods for the input forms
 */
public class Validation {

    boolean nullCheck(String in) {

        if (in.equals(null) || in.equals("")) {
            return true;
        }

        return false;
    }
}
