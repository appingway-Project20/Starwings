package com.example.admin.starwingsapp.adpaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.starwingsapp.R;

/**
 * Created by admin on 6/24/2017.
 */

public class BatchDetailsAdapter extends RecyclerView.Adapter<BatchDetailsAdapter.BatchDetailsViewHolder> {
    public BatchDetailsAdapter() {
    }

    @Override
    public BatchDetailsAdapter.BatchDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.batch_details_card_layout
                                                                    ,parent,false);
        BatchDetailsViewHolder viewHolder = new BatchDetailsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BatchDetailsAdapter.BatchDetailsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    class BatchDetailsViewHolder extends RecyclerView.ViewHolder {
        public BatchDetailsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
