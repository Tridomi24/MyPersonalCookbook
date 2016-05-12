package com.example.tmitchell.mypersonalcookbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Author: Tmitchell
 * Created: 08/04/2016.
 * Purpose: List adapter for the dynamic menu in the main activity
 *
 * Code adapted from: http://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
 * [ACCESSED ON 08/04/16]
 */
public class ListAdapter extends ArrayAdapter<RecipeDB> {
    int mainCount = 0;
    int starterCount = 0;
    int dessertCount = 0;
    int snackCount = 0;

    String firstDessert = null;
    String firstMain = null;
    String firstSnack = null;
    String firstStarter = null;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<RecipeDB> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.itemlistrow, null);
        }

        RecipeDB db = getItem(position);

        if (db != null) {
            TextView rID = (TextView) v.findViewById(R.id.recipeID_lv);
            TextView rTitle = (TextView) v.findViewById(R.id.recipeTitle_lv);
            TextView rCat = (TextView) v.findViewById(R.id.recipeCategory_header);

            if (rID != null) {
                rID.setText(Integer.toString(db.get_id()));
            }

            if (rTitle != null) {
                rTitle.setText(db.get_title());
            }

            //Makes the headers invisible if there are more than one that
            //category present in the list.
            if (rCat != null) {

                //sets the category textview based on the entry
                rCat.setText(db.get_category());

                //Desserts will set the header to visible for the first row
                if (rCat.getText().equals("Dessert")) {

                    assert rID != null;
                    if (dessertCount == 0 || rID.getText().equals(firstDessert)) {
                        rCat.setVisibility(View.VISIBLE);

                        firstDessert = (String) rID.getText();

                        dessertCount = +1;

                    } else if (dessertCount >= 1 && !rID.getText().equals(firstDessert)) {
                        rCat.setVisibility(View.GONE);
                    }
                }

                //Mains will set the header to visible for the first row
                if (rCat.getText().equals("Main")) {
                    assert rID != null;
                    if (mainCount == 0 || rID.getText().equals(firstMain)) {
                        rCat.setVisibility(View.VISIBLE);

                        firstMain = (String) rID.getText();

                        mainCount = +1;
                    } else if (mainCount >= 1 && !rID.getText().equals(firstMain)) {
                        rCat.setVisibility(View.GONE);
                    }
                }
                //Snack will set the header to visible for the first row
                if (rCat.getText().equals("Snack")) {

                    assert rID != null;
                    if (snackCount == 0 || rID.getText().equals(firstSnack)) {
                        rCat.setVisibility(View.VISIBLE);

                        firstSnack = (String) rID.getText();

                        snackCount = +1;
                    } else if (snackCount >= 1 && !rID.getText().equals(firstSnack)) {
                        rCat.setVisibility(View.GONE);
                    }
                }

                //Starters will set the header to visible for the first row
                if (rCat.getText().equals("Starter")) {

                    assert rID != null;
                    if (starterCount == 0 || rID.getText().equals(firstStarter)) {
                        rCat.setVisibility(View.VISIBLE);

                        firstStarter = (String) rID.getText();

                        starterCount = +1;
                    } else if (starterCount >= 1 && !rID.getText().equals(firstStarter)) {
                        rCat.setVisibility(View.GONE);
                    }
                }

            }

        }

        return v;
    }

}
