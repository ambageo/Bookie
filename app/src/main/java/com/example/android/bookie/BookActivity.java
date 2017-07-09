package com.example.android.bookie;

import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.TextView;

import static com.example.android.bookie.R.id.query;


public class BookActivity extends AppCompatActivity
implements LoaderCallbacks<List<Book>>{

    String userQuery;
    private BookAdapter bAdapter;
    /**
     * TextView that is displayed when the list is empty
     */
    private TextView bEmptyStateTextView;
    /**
     * Constant value for the earthquake loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int BOOK_LOADER_ID = 1;
    public static final String LOG_TAG= BookActivity.class.getName();
    private static final String GOOGLE_BOOKS_REQUEST="https://www.googleapis.com/books/v1/volumes?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_activity);

        Intent intent= getIntent();
        userQuery= intent.getStringExtra("User query");
        Log.v(LOG_TAG, "query transferred from MainActivity: "+ userQuery);

        // find a reference to the {@link ListView} in the layout
        ListView bookListView= (ListView) findViewById(R.id.list);

        // Create a new adapter that takes the list of books.
        bAdapter= new BookAdapter(this, new ArrayList<Book>());

        //set the adapter on the {@link ListView} to populate the list in the user interface
        bookListView.setAdapter(bAdapter);

        //set the view to show if the adapter is empty
        bEmptyStateTextView = (TextView) findViewById(R.id.empty);
        bookListView.setEmptyView(bEmptyStateTextView);

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager cm =
                (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        if(isConnected){
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
        }
        else{
            //if there is no connection, hide the loading indicator
            // and change the text message accordingly
            bEmptyStateTextView.setText(R.string.no_connection);
        }

    }

    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        StringBuilder builder= new StringBuilder();
        builder.append(GOOGLE_BOOKS_REQUEST);
        builder.append(userQuery);
        String bookQuery= builder.toString();
        Log.v(LOG_TAG, "final url is: "+ bookQuery);

        return new BookLoader(this, bookQuery);

    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {

        // Clear the adapter of previous book data
        bAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {bAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        bAdapter.clear();
    }


}
