package com.androidtutorialpoint.mycontacts.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidtutorialpoint.mycontacts.Contact;
import com.androidtutorialpoint.mycontacts.DBOpenHelper;
import com.androidtutorialpoint.mycontacts.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * ЭТОТ класс позволяет реализовать  RecyclerView, ContentProvider и Loader воедино!
 */

public class MyRecyclerCursorAdapter extends RecyclerView.Adapter<MyRecyclerCursorAdapter.ViewHolder> {

    CursorAdapter mCursorAdapter;
    Context mContext;
    private int itemLayout;


    public void onChange(Cursor cursor)
    {
        mCursorAdapter.swapCursor(cursor);
        notifyDataSetChanged();
    }

    public MyRecyclerCursorAdapter(Context context,Cursor cursor, int itemLayout) {
        this.mContext = context;
        this.mCursorAdapter = new CursorAdapter(mContext, cursor, 0) {

            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return LayoutInflater.from(context).inflate(
                        R.layout.contact_list_item,parent,false );
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {
                String contactName = cursor.getString(
                        cursor.getColumnIndex(DBOpenHelper.CONTACT_NAME));
                String contactPhone = cursor.getString(
                        cursor.getColumnIndex(DBOpenHelper.CONTACT_PHONE));

                String url = cursor.getString(
                        cursor.getColumnIndex(DBOpenHelper.URL));

                Integer contactId = cursor.getInt(
                        cursor.getColumnIndex(DBOpenHelper.CONTACT_ID));
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
                            .resize(200,200)
                            .centerCrop()
                            .into(iv);
            }
        };
        this.itemLayout = itemLayout;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Passing the binding operation to cursor loader
        mCursorAdapter.getCursor().moveToPosition(position); //EDITED: added this line as suggested in the comments below, thanks :)
        mCursorAdapter.bindView(holder.itemView, mContext, mCursorAdapter.getCursor());

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Passing the inflater job to the cursor-adapter
        View v = mCursorAdapter.newView(mContext, mCursorAdapter.getCursor(), parent);
        return new ViewHolder(v);
    }

    @Override public int getItemCount() {
        return mCursorAdapter.getCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
      //  public ImageView image;
        public TextView nameTextView,phoneTextView,idTextView;
        public ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            idTextView = (TextView) itemView.findViewById(R.id.contactId);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);
            iv = (ImageView)itemView.findViewById(R.id.imageDocIcon);

        }
    }
}
