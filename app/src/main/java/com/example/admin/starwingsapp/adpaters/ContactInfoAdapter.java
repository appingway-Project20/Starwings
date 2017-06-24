package com.example.admin.starwingsapp.adpaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

/**
 * Created by admin on 6/23/2017.
 */

public class ContactInfoAdapter extends RecyclerView.Adapter<ContactInfoAdapter.ViewHolder> {
    @Override
    public ContactInfoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_info_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactInfoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView home,phone;
        TextView addresstv,phnotv;
        public ViewHolder(View itemView) {
            super(itemView);
            home = (ImageView)itemView.findViewById(R.id.home);
            phone = (ImageView)itemView.findViewById(R.id.phone);
            addresstv = (TextView)itemView.findViewById(R.id.address);
            phnotv = (TextView)itemView.findViewById(R.id.phone_number);
        }
    }
}
