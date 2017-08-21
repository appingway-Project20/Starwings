package com.sachinstarwings.learning.destination.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;
import com.sachinstarwings.learning.destination.models.Publications;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 7/29/2017.
 */

public class PublicationsAdapter extends RecyclerView.Adapter<PublicationsAdapter.PublicationsViewHolder> {

    private Context context;
    private ArrayList<Publications> publicationsArrayList;
    public PublicationsAdapter(ArrayList<Publications> publicationsArrayList, Context context) {
        this.publicationsArrayList = publicationsArrayList;
        this.context = context;
    }

    @Override
    public PublicationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.publication_item_layout, parent, false);
        return new PublicationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PublicationsViewHolder holder, int position) {
        String imageLink = "http://starwingslearningdestination.com/php/web_api/";
        Picasso.with(context)
                .load(imageLink + publicationsArrayList.get(position).getmImageUrl())
                .placeholder(R.drawable.ic_picture_gallery)
                .error(R.drawable.ic_error_triangle)
                .fit()
                .centerCrop()
                .into(holder.bookImageIv);
        holder.bookTitleTv.setText(publicationsArrayList.get(position).getmImageName());

    }

    @Override
    public int getItemCount() {
        return publicationsArrayList.size();
    }

    class PublicationsViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImageIv;
        TextView bookTitleTv;
        public PublicationsViewHolder(View itemView) {
            super(itemView);
            bookImageIv = (ImageView)itemView.findViewById(R.id.book_image);
            bookTitleTv = (TextView)itemView.findViewById(R.id.book_name);
        }
    }
}
