package com.example.newshub.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newshub.R;
import com.example.newshub.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private ArrayList<News> data;
    private Context adapterContext;
    public NewsAdapter(ArrayList<News> data, Context adapterContext) {
        this.adapterContext=adapterContext;
        this.data = data;
    }


    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=LayoutInflater.from(adapterContext).
                inflate(R.layout.news_item,parent,false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
            String title=data.get(position).getTitle();
            String imageUrl=data.get(position).getImageUrl();
            holder.titleView.setText(title);
            ImageView view=holder.imageView;
            Glide.with(adapterContext)
                .load(imageUrl)
                .into(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView titleView;
        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_view);
            titleView=itemView.findViewById(R.id.title_text_view);
        }
    }
}
