package com.sachinstarwings.learning.destination.adpaters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;

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
        return new AboutUsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AboutUsAdapter.AboutUsViewHolder holder, int position) {
        switch(position){
            case 0:
                holder.tv1.setTextSize(20);
                holder.tv1.setText("STARWINGS LEARNING DESTINATION Pvt. Ltd.");
                holder.tv1.setTextColor(Color.BLACK);
                holder.tv2.setText(placeHolderText);
                holder.companyLogo.setImageResource(R.drawable.starwings_72);
                holder.sachin.setVisibility(View.INVISIBLE);
                break;
            case 1:
                holder.tv1.setTextSize(20);
                holder.tv1.setText("ABOUT SACHIN KAUSHIK");
                holder.tv1.setTextColor(Color.BLACK);
                holder.tv2.setText(" Sachin Kaushik is a throughout first class Graduate with distinction in B.Com from Delhi University. " +
                        "He obtained 100% marks in Cost Accounting and INCOME TAX.\n Now he is pursuing in Chartered Accountant(final) as" +
                        " well as Company Secretary (Prof.) as well as he passed exam of MCX, NCX. Now he is working as Financial Analyst " +
                        "in PaisaVassoli.com (a Financial Research Company).\n He is topper in Account in XII class in his Area.\n He is " +
                        "inspired by his mother Smt. Sunita Kaushik who always motivated and encouraged him.\n His technique of approaching the " +
                        "subject matter, style of revision and guidelines for preparation of examination are quite popular among students.");
                holder.companyLogo.setVisibility(View.INVISIBLE);
                holder.sachin.setImageResource(R.drawable.sachin);
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
    class AboutUsViewHolder extends RecyclerView.ViewHolder{
        TextView tv1,tv2;
        ImageView companyLogo,sachin;
        public AboutUsViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView)itemView.findViewById(R.id.company_name);
            tv2 = (TextView)itemView.findViewById(R.id.about_company);
            companyLogo = (ImageView)itemView.findViewById(R.id.logo);
            sachin = (ImageView) itemView.findViewById(R.id.sachin);
        }
    }
}
