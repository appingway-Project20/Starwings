package com.sachinstarwings.learning.destination.adpaters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sachinstarwings.learning.destination.PDFDownload_Async;
import com.sachinstarwings.learning.destination.R;
import com.sachinstarwings.learning.destination.models.TopicData;

import java.io.File;
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
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        holder.tpicauth.setText(arrayList.get(position).getAuthor());
        holder.topicupdate.setText(arrayList.get(position).getDate());
        holder.topicnm.setText(arrayList.get(position).getTitle());

        holder.topicno.setText(Integer.toString(position+1));
        holder.topdwn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "downloading Chapter", Toast.LENGTH_SHORT).show();
                PDFDownload_Async task=new PDFDownload_Async((Activity) context,arrayList.get(position).getTitle());
                //task.execute(arrayList.get(position).getLink());
                task.execute("http://starwingslearningdestination.com/php/web_api/"+arrayList.get(position).getLink());
                Toast.makeText(context, "Async task running"+"http://starwingslearningdestination.com/php/web_api/"+arrayList.get(position).getLink(), Toast.LENGTH_SHORT).show();
                holder.topdwn.setVisibility(View.GONE);
            }
        });
        holder.topicnm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onPdfClick(arrayList.get(position).getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView topicnm, tpicauth, topicupdate, topdwn,topicno;

        public myViewHolder(View itemView) {
            super(itemView);
            topicnm = (TextView) itemView.findViewById(R.id.titletv);
            topdwn = (TextView) itemView.findViewById(R.id.downloadtv);
            topicupdate = (TextView) itemView.findViewById(R.id.datetv);
            tpicauth = (TextView) itemView.findViewById(R.id.authtv);
            topicno=(TextView)itemView.findViewById(R.id.notv);
        }
    }
    private void onPdfClick(String title)
    {


        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mydownload/"+title+".pdf");

       // Uri uri=Uri.parse(Environment.getExternalStorageDirectory().getAbsolutePath()+"/sdcard/mydownload/"+title+".pdf");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/*");
        context.startActivity(intent);
    }
}
