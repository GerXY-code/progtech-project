package com.example.spotifly;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class HomePageActivity extends AppCompatActivity {

    ListView musicList;
    ImageView playButton;
    ImageView pauseButton;
    Integer length;
    String Position;
    ArrayList<CurrentMusic> musics = new ArrayList<>();

    TextView musicTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        musicList = (ListView)findViewById(R.id.music_list);
        playButton = (ImageView)findViewById(R.id.ic_music_play);
        pauseButton = (ImageView)findViewById(R.id.ic_music_stop);
        playButton.setVisibility(View.INVISIBLE);
        pauseButton.setVisibility(View.INVISIBLE);
        Playlist p = new Playlist();
        PlaylistCommand addToPlaylistCommand      = new PlaylistCommand(p, PlaylistAction.AddNewMusic,"armanen");
        PlaylistCommand removeFromPlaylistCommand = new PlaylistCommand(p, PlaylistAction.RemoveMusicFrom, "armanen");
        addToPlaylistCommand.call();
        removeFromPlaylistCommand.call();
        musicTitle = (TextView)findViewById(R.id.music_title);
        getMusics();


    }



    protected void getMusics(){


        AsyncGetMusics task = new AsyncGetMusics();
        try {
            musics = task.execute().get();
            Log.d("musics", musics.get(0).title);
            MusicBaseAdapter mbA = new MusicBaseAdapter(getApplicationContext(),musics);
            musicList.setAdapter(mbA);



        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Music m = new Music(this);



        musicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            Integer MusicClickCount = 1;


            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(MusicClickCount!=1){
                    MusicCommand pauseMusic = new MusicCommand(m,MusicAction.Replay,musics.get(position).rawID);
                    pauseMusic.call();
                    Position = musics.get(position).rawID;
                    musicTitle.setText(musics.get(position).title);
                    pauseButton.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.INVISIBLE);


                }
                else{
                    MusicCommand startMusic = new MusicCommand(m,MusicAction.Start,musics.get(position).rawID);
                    startMusic.call();
                    Position = musics.get(position).rawID;
                    musicTitle.setText(musics.get(position).title);
                    MusicClickCount++;
                    pauseButton.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.INVISIBLE);

                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicCommand startMusic = new MusicCommand(m,MusicAction.SeekToStart,Position);
                playButton.setVisibility(View.INVISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
                startMusic.call();
            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicCommand startMusic = new MusicCommand(m,MusicAction.Pause,Position);
                pauseButton.setVisibility(View.INVISIBLE);
                playButton.setVisibility(View.VISIBLE);
                startMusic.call();
            }
        });


    }











}
