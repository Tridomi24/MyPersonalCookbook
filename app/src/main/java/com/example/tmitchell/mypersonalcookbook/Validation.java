package com.example.tmitchell.mypersonalcookbook;

/**
 * Author: Tmitchell
 * Created: 01/05/2016.
 * Purpose: contains validation methods for the input forms used in multiple activities
 */
public class Validation {

    //method to check that a string value contains a value
    boolean nullCheck(String in) {

        return in == null || in.equals("");

    }

    //checks that the inputted time is greater than 0 mins under 5hrs
    boolean reasonableTime(int in) {

        return in >= 0 && in <= 300;

    }

    //Checks that the serves value is reasonable (below 20)
    boolean reasonableServes(int in) {

        return in >= 1 && in <= 20;

    }

}
