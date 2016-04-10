package com.example.tmitchell.mypersonalcookbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tmitchell on 08/04/2016.
 * Code adapted from: http://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
 * [ACCESSED ON 08/04/16]
 */
public class ListAdapter extends ArrayAdapter<RecipeDB> {


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
            TextView rCat = (TextView) v.findViewById(R.id.recipeCategory_lv);

            if (rID != null){
                rID.setText(Integer.toString(db.get_id()));
            }

            if (rTitle != null) {
                rTitle.setText(db.get_title());
            }

            if (rCat != null) {
                rCat.setText(db.get_category());
            }

        }

        return v;
    }

}
