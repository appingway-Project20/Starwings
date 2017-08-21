package com.sachinstarwings.learning.destination.adpaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;
import com.sachinstarwings.learning.destination.models.PapersData;

import java.util.ArrayList;

/**
 * Created by admin on 7/12/2017.
 */

public class PapersAdapter extends RecyclerView.Adapter<PapersAdapter.PapersHolder> {
    ArrayList<PapersData> papersData;
    ListItemClickListener listItemClickListener;

    public PapersAdapter(ArrayList<PapersData> fileNames, ListItemClickListener listItemClickListener) {
        this.papersData = fileNames;
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public PapersHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.papers_item_layout, parent, false);
        PapersHolder papersHolder = new PapersHolder(view);
        return papersHolder;
    }

    @Override
    public void onBindViewHolder(PapersHolder holder, int position) {
        holder.fileNameTv.setText(papersData.get(position).getmFilename());
        holder.yearTv.setText(papersData.get(position).getYear());


    }

    @Override
    public int getItemCount() {
        return papersData.size();
    }
    class PapersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fileNameTv, yearTv;
        public PapersHolder(View itemView) {
            super(itemView);
            fileNameTv = (TextView)itemView.findViewById(R.id.file_name);
            yearTv = (TextView)itemView.findViewById(R.id.year);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listItemClickListener.onListItemClicked(getAdapterPosition());
        }
    }
    public  interface ListItemClickListener{
        public void onListItemClicked(int itemPosition);
    }
}
