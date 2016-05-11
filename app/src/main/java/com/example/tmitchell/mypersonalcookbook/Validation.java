package com.example.tmitchell.mypersonalcookbook;

/**
 * Created by Tmitchell on 01/05/2016.
 * Purpose: contains validation methods for the input forms
 */
public class Validation {

    //method to check that a string value contains a value
    boolean nullCheck(String in) {

        if (in.equals(null) || in.equals("")) {
            return true;
        }

        return false;
    }

    //checks that the inputted time is greater than 0 mins under 5hrs
    boolean reasonableTime(int in) {

        if (in >= 0 && in <= 300) {
            return true;
        }

        return false;
    }

    //Checks that the serves value is reasonable (below 20)
    boolean reasonableServes(int in) {

        if (in >= 1 && in <= 20) {
            return true;
        }

        return false;
    }

}
