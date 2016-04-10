package com.example.tmitchell.mypersonalcookbook;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
/***************************************************************
    Code in this class adapted from:
    http://kb4dev.com/tutorial/android-listview/event-handling-
    in-listview--making-list-items-clickable
    [ACCESSED 10/04/2016]

    Created by Tmitchell on 10/04/2016.
 ***************************************************************/

public class ListClickHandler implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {

        Intent intent = new Intent(view.getContext(), ViewRecipe.class);

        view.getContext().startActivity(intent);

    }
}
