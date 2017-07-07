package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.starwingsapp.ChapterActivity;
import com.example.admin.starwingsapp.R;
import com.example.admin.starwingsapp.models.CourseChapterData;
import com.example.admin.starwingsapp.models.TopicData;

import java.util.ArrayList;

/**
 * Created by AKASH on 07-07-2017.
 */

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.myViewHolder> {
    public ArrayList<TopicData> arrayList = new ArrayList<>();
    public Context context;

    public TopicsAdapter(ArrayList<TopicData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topics_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        holder.tpicauth.setText(arrayList.get(position).getAuthor());
        holder.topicupdate.setText(arrayList.get(position).getDate());
        holder.topicupdate.setText(arrayList.get(position).getTitle());
        holder.topdwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "downloading data task", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView topicnm, tpicauth, topicupdate, topdwn;

        public myViewHolder(View itemView) {
            super(itemView);
            topicnm = (TextView) itemView.findViewById(R.id.titletv);
            topdwn = (TextView) itemView.findViewById(R.id.downloadtv);
            topicupdate = (TextView) itemView.findViewById(R.id.datetv);
            tpicauth = (TextView) itemView.findViewById(R.id.authtv);
        }
    }
}
