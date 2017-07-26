package com.example.admin.starwingsapp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Lalit on 12-07-2017.
 */

class DailyScheduleAdapter extends RecyclerView.Adapter<DailyScheduleAdapter.MyViewHolder>{
    private Context context;
    String[] days;
    private ListItemClickListener listener;

    public DailyScheduleAdapter(Context contetxt,String[] days,ListItemClickListener listener) {
        this.context = context;
        this.days = days;
        this.listener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_schedule_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyScheduleAdapter.MyViewHolder holder, int position) {
        //String url=urlList.get(position);

        holder.dayName.setText(days[position]);
        holder.dayName.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return days.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView dayName;

        public MyViewHolder(View itemView) {
            super(itemView);
            dayName= (TextView) itemView.findViewById(R.id.day_name);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            int listItemIndex = getAdapterPosition();
            listener.onListItemClicked(listItemIndex);
        }
    }
    public interface ListItemClickListener {
        void onListItemClicked(int position);
    }
}
