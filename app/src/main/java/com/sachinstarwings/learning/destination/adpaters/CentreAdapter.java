package com.sachinstarwings.learning.destination.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;

import java.util.ArrayList;

public class CentreAdapter extends RecyclerView.Adapter<CentreAdapter.myHolder> {

    ArrayList<String> arrayList;
    public Context context;
   // private int lastPosition = -1;
    public CentreAdapter(ArrayList<String> List, Context context){
        this.arrayList=List;
        this.context=context;
    }

    @Override
    public myHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.centers_item,parent,false);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(myHolder holder, int position) {
        holder.textView.setText(arrayList.get(position));
        setAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public myHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.centre_name);
        }
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        //if (position > lastPosition)
       // {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
           // lastPosition = position;
       // }
    }

    @Override
    public void onViewDetachedFromWindow(myHolder holder) {
        ((myHolder)holder).itemView.clearAnimation();
    }
}
