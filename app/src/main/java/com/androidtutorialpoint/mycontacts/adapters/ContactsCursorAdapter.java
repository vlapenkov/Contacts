package com.androidtutorialpoint.mycontacts.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtutorialpoint.mycontacts.DBOpenHelper;
import com.androidtutorialpoint.mycontacts.R;
import com.squareup.picasso.Picasso;

/**
 * Created by lapenkov on 04.12.2017.
 */

public class ContactsCursorAdapter extends CursorAdapter {
    public ContactsCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        return LayoutInflater.from(context).inflate( R.layout.contact_list_item,viewGroup,false );
    }



    @Override
    public void bindView(View view, Context context, Cursor cursor) {

      /*  ViewHolder holder  =   (ViewHolder)    view.getTag();

        holder.nameTextView.setText(contactName);
        holder.phoneTextView.setText(contactPhone);
        holder.idTextView.setText("Id:"+contactId); */

        String contactName = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CONTACT_NAME));
        String contactPhone = cursor.getString(cursor.getColumnIndex(DBOpenHelper.CONTACT_PHONE));
        String url = cursor.getString(cursor.getColumnIndex(DBOpenHelper.URL));
        Integer contactId = cursor.getInt(cursor.getColumnIndex(DBOpenHelper.CONTACT_ID));

        TextView nameTextView = (TextView) view.findViewById(R.id.nameTextView);
        TextView phoneTextView = (TextView) view.findViewById(R.id.phoneTextView);
        TextView idTextView = (TextView) view.findViewById(R.id.contactId);
        idTextView.setText("Id:"+contactId);
        nameTextView.setText(contactName);
        phoneTextView.setText(contactPhone);
       ImageView iv= (ImageView) view.findViewById(R.id.imageDocIcon);

        if (url!=null)
        Picasso
                .with(context)
                .load(url)
   //            .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                .into(iv);
    }


}
