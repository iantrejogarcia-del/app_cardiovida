package com.example.cardvascular.Musica;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cardvascular.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MyViewHolder> {

    private List<MusicList> list;
    private final Context context;
    private int playingPosition = 0;
    private final SongChangeListener songChangeListener;

    public MusicAdapter(List<MusicList> list, Context context) {
        this.list = list;
        this.context = context;
        this.songChangeListener = ((SongChangeListener) context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.music_adapter_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        MusicList music = list.get(position);

        if (music.isPlaying()) {
            playingPosition = position;
            holder.rootLayout.setBackgroundResource(R.drawable.round_back_blue_10);
        } else {
            holder.rootLayout.setBackgroundResource(R.drawable.round_back_10);
        }

        holder.title.setText(music.getTitle());
        holder.artist.setText(music.getArtist());
        holder.musicDuration.setText(music.getDuration());

        holder.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(playingPosition).setPlaying(false);
                music.setPlaying(true);

                songChangeListener.onchanged(position);
                notifyDataSetChanged();
            }
        });
    }

    public void updateList(List<MusicList> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout rootLayout;
        private TextView title;
        private TextView artist;
        private TextView musicDuration;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rootLayout = itemView.findViewById(R.id.rootLayout);
            title = itemView.findViewById(R.id.musicTittle);
            artist = itemView.findViewById(R.id.musicArtist);
            musicDuration = itemView.findViewById(R.id.musicDuration);
        }
    }
}