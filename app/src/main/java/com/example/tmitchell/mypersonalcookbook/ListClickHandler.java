package com.example.tmitchell.mypersonalcookbook;
/**
 * Authour: Tmitchell
 * Created: 10/04/2016.
 * PURPOSE: Class that handles when the dynamic navigation menu is clicked in the main class
 */

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

/***************************************************************
 * Code in this class adapted from:
 * http://kb4dev.com/tutorial/android-listview/event-handling-
 * in-listview--making-list-items-clickable
 * [ACCESSED 10/04/2016]
 * Created by Tmitchell on 10/04/2016.
 ***************************************************************/

public class ListClickHandler implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

        Intent intent = new Intent(view.getContext(), ViewRecipe.class);

        RecipeDB data = (RecipeDB) adapter.getItemAtPosition(position);

        int id = data.get_id();

        intent.putExtra("id", id);

        view.getContext().startActivity(intent);

    }
}
