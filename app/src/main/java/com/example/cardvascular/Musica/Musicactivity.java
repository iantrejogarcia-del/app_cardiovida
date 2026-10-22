package com.example.cardvascular.Musica;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cardvascular.MainActivity2;
import com.example.cardvascular.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Musicactivity extends AppCompatActivity implements SongChangeListener {

    private ImageButton btnimgregresar;
    private final List<MusicList> musicListList = new ArrayList<>();
    private RecyclerView musicRecyclerView;
    private MediaPlayer mediaPlayer;
    private TextView endTime, startTime;
    private boolean isPlaying = false;
    private SeekBar playerSeekBar;
    private ImageView playPauseImg;
    private Timer timer;
    private int currentSongListPosition = 0;
    private MusicAdapter musicAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decodeView = getWindow().getDecorView();
        int options = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decodeView.setSystemUiVisibility(options);

        setContentView(R.layout.activity_musicactivity);

        btnimgregresar=findViewById(R.id.btnimgregresar);
        btnimgregresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }

                if (timer != null) {
                    timer.cancel();
                    timer.purge();
                    timer = null;
                }

                Intent intent = new Intent(Musicactivity.this, MainActivity2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.izquierda1, R.anim.derecha1);
                finish();
            }
        });

        final LinearLayout searchBtn = findViewById(R.id.searchbtn);
        final LinearLayout menuBtn = findViewById(R.id.menubtn);
        musicRecyclerView = findViewById(R.id.musicRecyclerView);
        final CardView playPauseCard = findViewById(R.id.playPauseCard);
        playPauseImg = findViewById(R.id.playPauseImg);
        final ImageView nextBtn = findViewById(R.id.nextBtn);
        final ImageView prevBtn = findViewById(R.id.previousBtn);
        playerSeekBar = findViewById(R.id.playerSeekbar);

        startTime = findViewById(R.id.startTime);
        endTime = findViewById(R.id.endTime);

        musicRecyclerView.setHasFixedSize(true);
        musicRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mediaPlayer = new MediaPlayer();

        getMusicFiles();

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextSongListPosition = currentSongListPosition + 1;

                if (nextSongListPosition >= musicListList.size()) {
                    nextSongListPosition = 0;
                }

                musicListList.get(currentSongListPosition).setPlaying(false);
                musicListList.get(nextSongListPosition).setPlaying(true);

                musicRecyclerView.scrollToPosition(nextSongListPosition);
                onchanged(nextSongListPosition);
            }
        });

        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int prevSongListPosition = currentSongListPosition - 1;

                if (prevSongListPosition < 0) {
                    prevSongListPosition = musicListList.size() - 1;
                }

                musicListList.get(currentSongListPosition).setPlaying(false);
                musicListList.get(prevSongListPosition).setPlaying(true);

                musicRecyclerView.scrollToPosition(prevSongListPosition);
                onchanged(prevSongListPosition);
            }
        });

        playPauseCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    isPlaying = false;

                    mediaPlayer.pause();
                    playPauseImg.setImageResource(R.drawable.play_icon);
                } else {
                    isPlaying = true;
                    mediaPlayer.start();
                    playPauseImg.setImageResource(R.drawable.pause_icon);
                }
            }
        });

        playerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (isPlaying) {
                        mediaPlayer.seekTo(progress);
                    } else {
                        mediaPlayer.seekTo(0);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void getMusicFiles() {
        int[] musicFiles = {
                R.raw.agua,
                R.raw.bosque,
                R.raw.lluvia,
                R.raw.relajante,
        };

        for (int resId : musicFiles) {
            String title = getResources().getResourceEntryName(resId);
            String artist = "DESCONOCIDO";
            String duration = "00:00";

            MusicList music = new MusicList(title, artist, duration, false, resId);
            musicListList.add(music);
        }

        musicAdapter = new MusicAdapter(musicListList, Musicactivity.this);
        musicRecyclerView.setAdapter(musicAdapter);
    }

    @Override
    public void onchanged(int position) {
        currentSongListPosition = position;
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        try {
            mediaPlayer.setDataSource(this, Uri.parse("android.resource://" + getPackageName() + "/" + musicListList.get(position).getResourceId()));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(Musicactivity.this, "No se puede reproducir la pista", Toast.LENGTH_SHORT).show();
        }

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                final int getTotalDuration = mp.getDuration();

                String generateDuration = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(getTotalDuration),
                        TimeUnit.MILLISECONDS.toSeconds(getTotalDuration) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getTotalDuration)));

                endTime.setText(generateDuration);
                isPlaying = true;

                mp.start();

                playerSeekBar.setMax(getTotalDuration);

                playPauseImg.setImageResource(R.drawable.pause_icon);
            }
        });

        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final int getCurrentDuration = mediaPlayer.getCurrentPosition();

                        String generateDuration = String.format(Locale.getDefault(), "%02d:%02d",
                                TimeUnit.MILLISECONDS.toMinutes(getCurrentDuration),
                                TimeUnit.MILLISECONDS.toSeconds(getCurrentDuration) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(getCurrentDuration)));

                        playerSeekBar.setProgress(getCurrentDuration);
                        startTime.setText(generateDuration);
                    }
                });
            }
        }, 1000, 1000);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.purge();
                timer.cancel();

                isPlaying = false;

                playPauseImg.setImageResource(R.drawable.play_icon);

                playerSeekBar.setProgress(0);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();

        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (timer != null) {
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }
}
