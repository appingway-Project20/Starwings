package com.example.admin.starwingsapp.adpaters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

/**
 * Created by admin on 6/21/2017.
 */

public class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.AboutUsViewHolder> {
    String placeHolderText = null;
    public AboutUsAdapter(String text){
        placeHolderText = text;
    }
    @Override
    public AboutUsAdapter.AboutUsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        AboutUsViewHolder viewHolder = new AboutUsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AboutUsAdapter.AboutUsViewHolder holder, int position) {
        switch(position){
            case 0:holder.tv1.setTextSize(24);holder.tv1.setText("Starwings");
                holder.tv1.setTextColor(Color.BLACK);
                holder.tv2.setText(placeHolderText);break;
            case 1:
                holder.tv1.setTextSize(20);holder.tv1.setText("Experienced Faculty");
                holder.tv1.setTextColor(Color.BLACK);
                holder.tv2.setText(placeHolderText);
                holder.companyLogo.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
    class AboutUsViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;
        ImageView companyLogo;
        public AboutUsViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.company_name);
            tv2 = (TextView)itemView.findViewById(R.id.about_company);
            companyLogo = (ImageView)itemView.findViewById(R.id.logo);

        }
    }
}
