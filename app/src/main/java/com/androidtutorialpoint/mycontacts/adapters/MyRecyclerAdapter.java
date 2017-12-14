package com.androidtutorialpoint.mycontacts.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidtutorialpoint.mycontacts.Contact;
import com.androidtutorialpoint.mycontacts.R;

import java.util.List;

/**
 *  В проекте не используется но м. быть использован для работы со списками в памяти
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Contact> items;
    private int itemLayout;

    public MyRecyclerAdapter(List<Contact> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Contact item = items.get(position);
        holder.idTextView.setText(""+item.Id);
        holder.phoneTextView.setText(item.ContactPhone);
        holder.nameTextView.setText(item.ContactName);


        holder.itemView.setTag(item);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
      //  public ImageView image;
        public TextView nameTextView,phoneTextView,idTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            idTextView = (TextView) itemView.findViewById(R.id.contactId);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            phoneTextView = (TextView) itemView.findViewById(R.id.phoneTextView);

        }
    }
}
