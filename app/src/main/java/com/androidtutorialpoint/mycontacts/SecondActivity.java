package com.androidtutorialpoint.mycontacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidtutorialpoint.mycontacts.adapters.ContactsCursorAdapter;
import com.androidtutorialpoint.mycontacts.adapters.MyRecyclerAdapter;
import com.androidtutorialpoint.mycontacts.adapters.MyRecyclerCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    MyRecyclerAdapter recyclerAdapter;
    MyRecyclerCursorAdapter recyclerCursorAdapter;
    List<Contact> list ;
    public static final Uri CONTENT_URI = ContactsProvider.CONTENT_URI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        recyclerView.setHasFixedSize(true);


        recyclerCursorAdapter = new MyRecyclerCursorAdapter(this,null,R.layout.contact_list_item);

        recyclerView.setAdapter(recyclerCursorAdapter );

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //recyclerView.setItemAnimator(new DefaultItemAnimator());
      getSupportLoaderManager().initLoader(0, null, this);

    }



    public void update (View view) {
        ContentValues cv = new ContentValues();
        cv.put(DBOpenHelper.CONTACT_NAME, "name Sergey2");
        cv.put(DBOpenHelper.CONTACT_PHONE, "email Sergey2");
        Uri newUri = getContentResolver().insert(CONTENT_URI, cv);
        Log.d("MainActivity", "insert, result Uri : " + newUri.toString());



    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,CONTENT_URI,null,null,null,null);

    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        recyclerCursorAdapter.onChange(cursor);



    }
    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        recyclerCursorAdapter.onChange(null);

    }


}
