package com.example.cs310newsapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    Context ctx;
    ArrayList<News> data;

    public NewsAdapter(Context ctx, ArrayList<News> data) {
        this.ctx = ctx;
        this.data = data;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.layout_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.setIsRecyclable(false);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, ActivityDetail.class);
                i.putExtra("id",data.get(holder.getAdapterPosition()).getId());
                ctx.startActivity(i);
            }
        });

        CS310NewsApp app = (CS310NewsApp)((AppCompatActivity)ctx).getApplication();
        holder.mTitle.setText(data.get(holder.getAdapterPosition()).getTitle());
        holder.mDate.setText(data.get(holder.getAdapterPosition()).getDate());
        holder.downloadImage(app.srv, data.get(holder.getAdapterPosition()).getImage());



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle, mDate;
        CardView cardView;
        ImageView imageView;
        boolean imageDownloaded;

        Handler imgHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {

                Bitmap img = (Bitmap)msg.obj;
                imageView.setImageBitmap(img);
                imageDownloaded = true;
                return true;
            }
        });



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle=itemView.findViewById(R.id.mainTitle);
            mDate=itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardview);


        }



        public void downloadImage(ExecutorService srv, String path){

            if (!imageDownloaded){

                NewsRepository repo = new NewsRepository();
                repo.downloadImage(srv,imgHandler,path);


            }



        }
    }
}
