package com.example.spotifly;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class my_playlist_activity extends AppCompatActivity {

    ListView musicList;
    FloatingActionButton playButton,pauseButton,removeFromPlaylistButton;

    ImageView music_cover;

    Integer length,currentMusicID;
    String Position;
    ArrayList<CurrentMusic> musics = new ArrayList<>();

    ArrayList<Integer> musicsAdded = new ArrayList<>();

    TextView musicTitle, music_author;
    Button playlist_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_playlist);
        musicList = (ListView)findViewById(R.id.music_list);
        playButton = (FloatingActionButton) findViewById(R.id.action_start);
        pauseButton = (FloatingActionButton) findViewById(R.id.action_pause);
        music_cover = (ImageView)findViewById(R.id.music_cover);
        music_author = (TextView)findViewById(R.id.music_author);
        music_cover.setVisibility(View.INVISIBLE);

        AudioPlayer au = new AudioPlayer(2,pauseButton,playButton);
        au.audioPlayerSetState();

        removeFromPlaylistButton = (FloatingActionButton)findViewById(R.id.remove_btn_from_playlist);

        playlist_btn = (Button)findViewById(R.id.playlist_btn);

        musicTitle = (TextView)findViewById(R.id.music_title);
        getMusics();
        checkTheAddedMusics();
        Music m = new Music(this);

    }

    public void checkTheAddedMusics(){
        CheckTheMusicStateAsync task = new CheckTheMusicStateAsync();
        try {
            musicsAdded = task.execute().get();
            //Log.d("musicsAddedAlready", musicsAdded.get(0).toString());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    protected void getMusics(){


        AsyncGetPlaylistMusic task = new AsyncGetPlaylistMusic();
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
                    music_author.setText(musics.get(position).author);

                    AudioPlayer au = new AudioPlayer(1,pauseButton,playButton);
                    au.audioPlayerSetState();

                    String variableValue = musics.get(position).rawID;
                    music_cover.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    music_cover.setVisibility(View.VISIBLE);
                    checkTheAddedMusics();
                    currentMusicID = musics.get(position).id;
                    //Log.d("currID", currentMusicID.toString());
                }
                else{
                    MusicCommand startMusic = new MusicCommand(m,MusicAction.Start,musics.get(position).rawID);
                    startMusic.call();
                    Position = musics.get(position).rawID;
                    musicTitle.setText(musics.get(position).title);
                    music_author.setText(musics.get(position).author);
                    MusicClickCount++;
                    AudioPlayer au = new AudioPlayer(1,pauseButton,playButton);
                    au.audioPlayerSetState();
                    String variableValue = musics.get(position).rawID;
                    music_cover.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    music_cover.setVisibility(View.VISIBLE);
                    checkTheAddedMusics();

                    currentMusicID = musics.get(position).id;
                    //Log.d("currID", currentMusicID.toString());
                }
            }
        });

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicCommand startMusic = new MusicCommand(m,MusicAction.SeekToStart,Position);
                AudioPlayer au = new AudioPlayer(1,pauseButton,playButton);
                au.audioPlayerSetState();
                startMusic.call();

            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicCommand startMusic = new MusicCommand(m,MusicAction.Pause,Position);
                AudioPlayer au = new AudioPlayer(0,pauseButton,playButton);
                au.audioPlayerSetState();
                startMusic.call();

            }
        });

        removeFromPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playlist p = new Playlist();
                PlaylistCommand removeFromPlaylist = new PlaylistCommand(p,PlaylistAction.RemoveMusicFrom,currentMusicID);
                try {
                    removeFromPlaylist.call();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        playlist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(my_playlist_activity.this, LandingPageActivity.class));
            }
        });


    }











}
