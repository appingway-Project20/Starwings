package com.example.admin.starwingsapp.adpaters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

/**
 * Created by admin on 6/23/2017.
 */

public class BatchesAdapter extends RecyclerView.Adapter<BatchesAdapter.BatchesViewHolder> {
    String courses[];
    private ListItemClickListener listener;

    public BatchesAdapter(String[] courses) {
        this.courses = courses;
    }

    public BatchesAdapter(String[] courses, ListItemClickListener listener){
        this.courses = courses;
        this.listener = listener;
    }
    @Override
    public BatchesAdapter.BatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_batches,parent,false);
        return new BatchesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BatchesAdapter.BatchesViewHolder holder, int position) {
        holder.coursetv.setText(courses[position]);
        holder.coursetv.setTextColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return courses.length;
    }
    class BatchesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView coursetv;
        public BatchesViewHolder(View itemView) {
            super(itemView);
            coursetv = (TextView)itemView.findViewById(R.id.course_name);
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
