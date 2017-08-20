package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.admin.starwingsapp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by admin on 7/14/2017.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImagesHolder> {

    private static final String TAG = GalleryAdapter.class.getSimpleName();
    ArrayList<String> imageLinks;
    Context context;

    private OnListItemClickListener listItemClickListener;

    public GalleryAdapter(ArrayList<String> imageLinks, Context context, OnListItemClickListener listItemClickListener) {
        this.imageLinks = imageLinks;
        this.context = context;
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public GalleryAdapter.ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_layout, parent, false);
        ImagesHolder imagesHolder = new ImagesHolder(v);
        return imagesHolder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ImagesHolder holder, int position) {
        String link = "http://starwingslearningdestination.com/php/web_api/" + imageLinks.get(position);
        link = link.replaceAll("\\s+","");
        Log.d(TAG,"link: "+link);
        Picasso.with(context)
                .load(link)
                .placeholder(R.drawable.ic_picture_gallery)
                .error(R.drawable.ic_error_triangle)
                .resize(320,240)
                .centerCrop()
                .into(holder.galleryIv);

    }

    @Override
    public int getItemCount() {
        int size = 0;

            if(!imageLinks.isEmpty() && imageLinks!= null){
                size = imageLinks.size();
                Log.d(TAG,"size: "+size);

                return size;
            }



        return size;
    }
    class ImagesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView galleryIv;
        public ImagesHolder(View itemView) {
            super(itemView);
            galleryIv = (ImageView)itemView.findViewById(R.id.gallery_image);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listItemClickListener.onListItemClicked(position);
        }
    }
    public interface OnListItemClickListener{
        public void onListItemClicked(int position);
    }
}
