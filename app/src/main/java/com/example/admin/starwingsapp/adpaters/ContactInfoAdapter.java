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
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ContactInfoAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView hophone,bophone;
        TextView home,hoaddresstv,hophnotv,bo,boaddresstv,bophnotv;
        public ViewHolder(View itemView) {
            super(itemView);
            home = (TextView)itemView.findViewById(R.id.home);
            hophone = (ImageView)itemView.findViewById(R.id.ho_phone);
            hoaddresstv = (TextView)itemView.findViewById(R.id.ho_address);
            hophnotv = (TextView)itemView.findViewById(R.id.ho_phone_number);
            bo = (TextView)itemView.findViewById(R.id.bo);
            bophone = (ImageView)itemView.findViewById(R.id.bo_phone);
            boaddresstv = (TextView)itemView.findViewById(R.id.bo_address);
            bophnotv = (TextView)itemView.findViewById(R.id.bo_phone_number);
        }
    }
}
