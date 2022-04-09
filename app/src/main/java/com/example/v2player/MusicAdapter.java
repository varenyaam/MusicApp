package com.example.v2player;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyviewHolder>{

    private Context mContext;
    private ArrayList<com.example.v2player.MusicFiles>mFiles;

    MusicAdapter(Context mContext,ArrayList<com.example.v2player.MusicFiles>mFiles)
    {
        this.mFiles = mFiles;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.music_item,parent,false);
        return new MyviewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {

        holder.file_name.setText(mFiles.get(position).getTitle());
        byte[] image = getalbumArt(mFiles.get(position).getPath());
        if (image !=null)
            {
                Glide.with(mContext).asBitmap().load(image).into(holder.album_art);
            }
        else {
            Glide.with(mContext).load(R.drawable.ic_launcher_background);
        }
        }


    @Override
    public int getItemCount() {
        return mFiles.size();
    }
//change to static
    public static class MyviewHolder extends RecyclerView.ViewHolder
    {
        TextView file_name;
        ImageView album_art;
        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            file_name = itemView.findViewById(R.id.music_file_name);
            album_art = itemView.findViewById(R.id.music_img);

        }
    }
private byte[]getalbumArt(String uri)

    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
