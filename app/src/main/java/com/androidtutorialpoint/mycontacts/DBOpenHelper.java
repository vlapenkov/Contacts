package com.androidtutorialpoint.mycontacts;

/**
 * Created by lapenkov on 04.12.2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

    //Constants for db name and version
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 10;

    //Constants for table and columns
    public static final String TABLE_CONTACTS = "contacts";
    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_NAME = "contactName";
    public static final String CONTACT_PHONE = "contactPhone";
    public static final String URL = "url";
    public static final String CONTACT_CREATED_ON = "contactCreationTimeStamp";

    public static final String[] ALL_COLUMNS =
            {CONTACT_ID,CONTACT_NAME,CONTACT_PHONE,URL,CONTACT_CREATED_ON};

    //Create Table
    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_CONTACTS + " (" +
                    CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CONTACT_NAME + " TEXT, " +
                    CONTACT_PHONE + " TEXT, " +
                    URL + " TEXT, " +
                    CONTACT_CREATED_ON + " TEXT default CURRENT_TIMESTAMP" +
                    ")";
    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
        ContentValues cv = new ContentValues();
    /*    for (int i = 1; i <= 3; i++) {
            cv.put(CONTACT_NAME, "name " + i);
            cv.put(CONTACT_PHONE, "email " + i);
            sqLiteDatabase.insert(TABLE_CONTACTS, null, cv);
        } */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

   public void createContact()
   {
      SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       cv.put(CONTACT_NAME, "name one");
       cv.put(CONTACT_PHONE, "email one");
       db.insert(TABLE_CONTACTS, null, cv);
   }
}
