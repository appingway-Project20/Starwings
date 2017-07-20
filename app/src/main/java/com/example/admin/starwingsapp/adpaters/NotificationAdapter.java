package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

import java.util.ArrayList;

/**
 * Created by AKASH on 21-06-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.myViewHolder> {

    public ArrayList<String> arrayList=new ArrayList<>();
    public Context context;

    public NotificationAdapter(ArrayList<String> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, int position) {
        holder.nottit.setText("Click to see the notifications");
        if (getItemCount()==0) {
            holder.notdet.setText("There are no new notifications till now!");
        }else{
            holder.notdet.setText(arrayList.get(position));
        }
        holder.notcv.setVisibility(View.GONE);

        holder.nottitrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.notcv.getVisibility() == View.VISIBLE)
                       holder.notcv.setVisibility(View.GONE);
                else {
                    holder.notcv.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView notdet,nottit;
        RelativeLayout nottitrl;
        CardView notcv;
        public myViewHolder(View itemView) {
            super(itemView);
            nottitrl= (RelativeLayout) itemView.findViewById(R.id.nottitle);
            notcv= (CardView) itemView.findViewById(R.id.notcv);
            notdet= (TextView) itemView.findViewById(R.id.notcvtv);
            nottit= (TextView) itemView.findViewById(R.id.nottitletv);
        }
    }
}
