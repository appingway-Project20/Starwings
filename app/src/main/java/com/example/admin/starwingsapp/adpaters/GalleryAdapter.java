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

    public GalleryAdapter(ArrayList<String> imageLinks, Context context) {
        this.imageLinks = imageLinks;
        this.context = context;
    }

    @Override
    public GalleryAdapter.ImagesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item_layout, parent, false);
        ImagesHolder imagesHolder = new ImagesHolder(v);
        return imagesHolder;
    }

    @Override
    public void onBindViewHolder(GalleryAdapter.ImagesHolder holder, int position) {
        String link = "http://starwing.appingway.com/php/web_api/" + imageLinks.get(position);
        Log.d(TAG,"link: "+link);
        Picasso.with(context)
                .load(link)
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
    class ImagesHolder extends RecyclerView.ViewHolder {
        ImageView galleryIv;
        public ImagesHolder(View itemView) {
            super(itemView);
            galleryIv = (ImageView)itemView.findViewById(R.id.gallery_image);
        }
    }
}
