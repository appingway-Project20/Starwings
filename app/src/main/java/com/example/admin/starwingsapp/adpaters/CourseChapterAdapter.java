package com.example.admin.starwingsapp.adpaters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.starwingsapp.ChapterActivity;
import com.example.admin.starwingsapp.CourseActivity;
import com.example.admin.starwingsapp.CoursePapersActivity;
import com.example.admin.starwingsapp.PapersActivity;
import com.example.admin.starwingsapp.R;
import com.example.admin.starwingsapp.TopicsActivity;
import com.example.admin.starwingsapp.models.CourseChapterData;

import java.util.ArrayList;

/**
 * Created by AKASH on 07-07-2017.
 */

public class CourseChapterAdapter extends RecyclerView.Adapter<CourseChapterAdapter.myViewHolder>{

    public ArrayList<CourseChapterData> arrayList=new ArrayList<>();
    public Context context;
    public CourseChapterAdapter(ArrayList<CourseChapterData> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_chapter_item,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.courseid.setText(arrayList.get(position).getCourse_id());
        holder.coursename.setText(arrayList.get(position).getCourse_name());
        holder.noc.setText(arrayList.get(position).getNo_of_chapters());
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(context instanceof ChapterActivity){
                    intent=new Intent(context, TopicsActivity.class);
                    intent.putExtra("cid",arrayList.get(position).getCourse_id());
                    intent.putExtra("cname",arrayList.get(position).getCourse_name());
                    context.startActivity(intent);
                }
                else if(context instanceof CourseActivity){
                    intent=new Intent(context, ChapterActivity.class);
                    intent.putExtra("cid",arrayList.get(position).getCourse_id());
                    context.startActivity(intent);
                }
                else if(context instanceof CoursePapersActivity){
                    intent = new Intent(context, PapersActivity.class);
                    intent.putExtra("cid",arrayList.get(position).getCourse_id());
                    context.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
    TextView courseid,coursename,noc;
    ConstraintLayout root;

    public myViewHolder(View itemView) {
        super(itemView);

        courseid = (TextView) itemView.findViewById(R.id.cid);
        coursename = (TextView) itemView.findViewById(R.id.cn);
        noc = (TextView) itemView.findViewById(R.id.noc);
        root= (ConstraintLayout) itemView.findViewById(R.id.root);
    }
}
}
