package com.sachinstarwings.learning.destination.adpaters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;
import com.sachinstarwings.learning.destination.models.Stock;

import java.util.ArrayList;

/**
 * Created by admin on 7/30/2017.
 */

public class StockRateAdapter extends RecyclerView.Adapter<StockRateAdapter.StockRateViewHolder> {

    private ArrayList<Stock> stocks;
    public StockRateAdapter(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public StockRateViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_rate_item, parent, false);


        return new StockRateViewHolder(v);
    }

    @Override
    public void onBindViewHolder(StockRateViewHolder holder, int position) {
        holder.companyNameTv.setText("Company: "+ stocks.get(position).getmCompanyName());
        holder.stockRateTv.setText("Stock rate: "+ stocks.get(position).getmStockRate());

    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }
    class StockRateViewHolder extends RecyclerView.ViewHolder {
        TextView companyNameTv, stockRateTv;
        public StockRateViewHolder(View itemView) {
            super(itemView);
            companyNameTv = (TextView)itemView.findViewById(R.id.company_name);
            stockRateTv = (TextView)itemView.findViewById(R.id.stock_rate_tv);

        }
    }
}
