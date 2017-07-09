package com.example.android.bookie;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A {@link BookAdapter} knows how to create a list item layout for each book
 * in the data source (a list of {@link Book} objects).
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */

public class BookAdapter extends ArrayAdapter<Book>{

    public BookAdapter(Activity context, ArrayList<Book> books){

        //Initializing the Adapter's internal storage for the context and the list.
        //Calling the superclass constructor. We are passing 0 as the second argument since we will
        //be generating and using a custom listView.
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //check whether the existing view is being reused, otherwise inflate it
        View listItemView= convertView;
        if(listItemView==null){
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent, false);
        }

        //get the data associated with the specified position
        Book currentBook= getItem(position);

        //find the TextView with id title
        TextView titleView= (TextView) listItemView.findViewById(R.id.title);
        titleView.setText(currentBook.getTitle());

        //find the TextView with id author
        TextView authorView= (TextView) listItemView.findViewById(R.id.author);
        authorView.setText(currentBook.getAuthor());

        //find the TextView with id publisher
        TextView publisherView= (TextView) listItemView.findViewById(R.id.publisher);
        publisherView.setText(currentBook.getPublisher());

        //find the TextView with id category
        TextView categoryView= (TextView) listItemView.findViewById(R.id.category);
        categoryView.setText(currentBook.getCategory());

        return listItemView;
    }


}
