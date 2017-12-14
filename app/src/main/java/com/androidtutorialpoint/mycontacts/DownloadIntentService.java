package com.androidtutorialpoint.mycontacts;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by lapenkov on 04.12.2017.
 */

public class DownloadIntentService extends IntentService {
    public static final String PENDING_RESULT = "pending_result";




    public DownloadIntentService() {
        super("DownloadIntentService");
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
      try {
        AssetManager am = getAssets();
        InputStream inputStream = am.open("contacts.json");


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;

        i = inputStream.read();
        while (i != -1)
        {
            if (i!=13 && i!=10)
                byteArrayOutputStream.write(i);
            i = inputStream.read();
        }
        inputStream.close();
        String result =byteArrayOutputStream.toString();

        JSONObject jObject = new JSONObject(result);
        JSONArray jArray = jObject.getJSONArray("contacts");
        for ( i=0; i < jArray.length(); i++)
        {

            JSONObject oneObject = jArray.getJSONObject(i);
            // Pulling items from the array
            String name = oneObject.getString("name");
            String email = oneObject.getString("email");
            String url = oneObject.getString("url");

            ContentValues cv = new ContentValues();
            cv.put(DBOpenHelper.CONTACT_NAME,name);
            cv.put(DBOpenHelper.CONTACT_PHONE, email);
            if (url!=null) cv.put(DBOpenHelper.URL, url);
            Uri newUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, cv);
            Log.d("MainActivity", "insert, result Uri : " + newUri.toString());

        }

    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }



      /*   AndroidNetworking.get("http://dev3.yst.ru/api/peopleapi")
                 .build()
                 .getAsJSONArray(new JSONArrayRequestListener() {
                     @Override
                     public void onResponse(JSONArray response) {
                         try {
                             for ( int i=0; i < response.length(); i++)
                             {

                                 JSONObject oneObject = response.getJSONObject(i);
                                 // Pulling items from the array
                                 String name = oneObject.getString("name");
                                 String email = oneObject.getString("age");
                                 String url = "http://terminal.yst.ru/Content/img/YST.png";

                                 ContentValues cv = new ContentValues();
                                 cv.put(DBOpenHelper.CONTACT_NAME,name);
                                 cv.put(DBOpenHelper.CONTACT_PHONE, email);
                                 if (url!=null) cv.put(DBOpenHelper.URL, url);
                                 Uri newUri = getContentResolver().insert(ContactsProvider.CONTENT_URI, cv);
                                 Log.d("MainActivity", "insert, result Uri : " + newUri.toString());

                             }
                             //   return response.length();
                         }catch (Exception e) {
                             Log.d("error","error");
                         }
                     }
                     @Override
                     public void onError(ANError error) {
                         Log.d("error","error");
                         // handle error
                     }
                 }); */
     }
    }

