package com.example.android.bookie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.android.bookie.R.id.query;

/**
 * Created by georgeampartzidis on 9/7/17.
 */

public class MainActivity extends AppCompatActivity {

    public static final String LOG_TAG= MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);



        Button searchButton= (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent= new Intent(MainActivity.this, BookActivity.class);
                // Find the EditText and get the string the user inserts for the query.
                EditText queryText= (EditText) findViewById(R.id.query);
                String query= queryText.getText().toString();
                query= query.replace(" ", "");
                intent.putExtra("User query", query);
                startActivity(intent);
                Log.v(LOG_TAG, "Query inserted by the user is: " +query);
            }
        });
    }




}