package com.sachinstarwings.learning.destination.adpaters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sachinstarwings.learning.destination.R;
import com.sachinstarwings.learning.destination.models.Video;

import java.util.ArrayList;

/**
 * Created by admin on 7/10/2017.
 */

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.VideoHolder> {
    ArrayList<Video> videos;

    ListItemClickListener listItemClickListener;
    private static final String TAG = VideosAdapter.class.getSimpleName();

    public VideosAdapter(ArrayList<Video> videos , ListItemClickListener listItemClickListener) {
        this.videos = videos;
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public VideosAdapter.VideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_layout, parent,false);
        VideoHolder videoHolder = new VideoHolder(v);
        return videoHolder;
    }

    @Override
    public void onBindViewHolder(VideosAdapter.VideoHolder holder, int position) {
        holder.fileNameTv.setText(videos.get(position).getmVideoName());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }
    class VideoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView fileNameTv;
        public VideoHolder(View itemView) {
            super(itemView);
            fileNameTv = (TextView)itemView.findViewById(R.id.video_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "item clicked at position:"+ getAdapterPosition());
            listItemClickListener.onListItemClicked(getAdapterPosition());
        }
    }
    public interface ListItemClickListener{
         void onListItemClicked(int position);
    }
}
