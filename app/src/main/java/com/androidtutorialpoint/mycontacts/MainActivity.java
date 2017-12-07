package com.androidtutorialpoint.mycontacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
//import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;


import com.androidtutorialpoint.mycontacts.adapters.ContactsCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{


    private ContactsCursorAdapter cursorAdapter;


   public static final Uri CONTENT_URI = ContactsProvider.CONTENT_URI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cursorAdapter = new ContactsCursorAdapter(this,null,0);
       ListView list = (ListView) findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
       getSupportLoaderManager().initLoader(0, null, this);


       // Picasso.with(this).setLoggingEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             /*       Intent intent = new Intent(MainActivity.this, DownloadIntentService.class);
                    startService(intent);
                    */

                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

            }
        });
    }




    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        cursorAdapter.swapCursor(cursor);


    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }

    public void add(View view) {
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.CONTACT_NAME, "name new");
        cv.put(DBOpenHelper.CONTACT_PHONE, "email new");
        Uri newUri = getContentResolver().insert(CONTENT_URI, cv);
        Log.d("MainActivity", "insert, result Uri : " + newUri.toString());

    }

    public void update (View view) {
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.CONTACT_NAME, "name 15");
        cv.put(DBOpenHelper.CONTACT_PHONE, "email 15");
        Uri uri = ContentUris.withAppendedId(CONTENT_URI, 3);
        int cnt = getContentResolver().update(uri, cv, null, null);
    }

    public void delete(View view) {
        Uri uri = ContentUris.withAppendedId(CONTENT_URI, 3);
        int cnt = getContentResolver().delete(uri, null, null);
    }




}