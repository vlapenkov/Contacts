package com.androidtutorialpoint.mycontacts;

import android.database.Cursor;

/**
 * Created by lapenkov on 07.12.2017.
 */

public class Contact {


    public int Id;
    public String ContactName,ContactPhone,Url;


    public Contact(int id , String contactName, String contactPhone, String url) {
        Id=id;
        ContactName= contactName;
        ContactPhone=contactPhone;
        Url=url;

    }

    public static Contact fromCursor(Cursor cursor)
    {
        Integer id = cursor.getInt(cursor.getColumnIndexOrThrow(DBOpenHelper.CONTACT_ID));
        String contactName = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.CONTACT_NAME));
        String contactPhone = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.CONTACT_PHONE));
        String url = cursor.getString(cursor.getColumnIndexOrThrow(DBOpenHelper.URL));


        return new Contact(id,contactName,contactPhone,url);

    }
}
