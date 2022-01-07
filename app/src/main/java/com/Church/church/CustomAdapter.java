package com.Church.church;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    private ArrayList<VideoList> arrayList;
    private Context context;
    private String mUrl;


    public CustomAdapter(ArrayList<VideoList> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        Glide.with(holder.itemView)
                .load(arrayList.get(position).getThumbnail())
                .into(holder.v_thumbnail);
        holder.v_date.setText(arrayList.get(position).getDate());
        holder.v_title.setText(arrayList.get(position).getTitle());
        holder.v_preacher.setText(arrayList.get(position).getPreacher());

    }


    @Override
    public int getItemCount() {
        // 삼합 연산자
        return (arrayList != null ? arrayList.size() : 0);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView v_thumbnail;
        TextView v_date;
        TextView v_title;
        TextView v_preacher;


        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            this.v_thumbnail = itemView.findViewById(R.id.v_thumbnail);
            this.v_date = itemView.findViewById(R.id.v_date);
            this.v_title = itemView.findViewById(R.id.v_title);
            this.v_preacher = itemView.findViewById(R.id.v_preacher);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAbsoluteAdapterPosition(); // 1번
                    if (pos != RecyclerView.NO_POSITION) {    // 2번
                        mUrl = arrayList.get(pos).getUrl();
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mUrl));
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                    Toast.makeText(context, "오직은혜^^", Toast.LENGTH_SHORT).show();
                }
            });


        }
    }

    public void addItem(VideoList item) {
        arrayList.add(item);
    }

    public void setItems(ArrayList<VideoList> items) {
        this.arrayList = items;
    }

    public VideoList getItem(int position) {
        return arrayList.get(position);
    }

    public void setItem(int position, VideoList item) {
        arrayList.set(position, item);
    }

}