package com.example.admin.starwingsapp.adpaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.starwingsapp.R;

import java.util.ArrayList;

/**
 * Created by admin on 7/12/2017.
 */

public class PapersAdapter extends RecyclerView.Adapter<PapersAdapter.PapersHolder> {
    ArrayList<String> fileNames;
    ListItemClickListener listItemClickListener;

    public PapersAdapter(ArrayList<String> fileNames, ListItemClickListener listItemClickListener) {
        this.fileNames = fileNames;
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
        holder.fileNameTv.setText(fileNames.get(position));
    }

    @Override
    public int getItemCount() {
        return fileNames.size();
    }
    class PapersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView fileNameTv;
        public PapersHolder(View itemView) {
            super(itemView);
            fileNameTv = (TextView)itemView.findViewById(R.id.file_name);
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
