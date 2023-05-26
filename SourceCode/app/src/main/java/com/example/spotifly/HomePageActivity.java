package com.example.spotifly;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class HomePageActivity extends AppCompatActivity {

    ListView musicList;
    FloatingActionButton playButton,pauseButton;

    ImageView music_cover;

    Integer length;
    String Position;
    ArrayList<CurrentMusic> musics = new ArrayList<>();

    TextView musicTitle, music_author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        musicList = (ListView)findViewById(R.id.music_list);
        playButton = (FloatingActionButton) findViewById(R.id.action_start);
        pauseButton = (FloatingActionButton) findViewById(R.id.action_pause);
        music_cover = (ImageView)findViewById(R.id.music_cover);
        music_author = (TextView)findViewById(R.id.music_author);
        music_cover.setVisibility(View.INVISIBLE);
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
                    music_author.setText(musics.get(position).author);
                    pauseButton.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.INVISIBLE);
                    String variableValue = musics.get(position).rawID;
                    music_cover.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    music_cover.setVisibility(View.VISIBLE);

                }
                else{
                    MusicCommand startMusic = new MusicCommand(m,MusicAction.Start,musics.get(position).rawID);
                    startMusic.call();
                    Position = musics.get(position).rawID;
                    musicTitle.setText(musics.get(position).title);
                    music_author.setText(musics.get(position).author);
                    MusicClickCount++;
                    pauseButton.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.INVISIBLE);
                    String variableValue = musics.get(position).rawID;
                    music_cover.setImageResource(getResources().getIdentifier(variableValue, "drawable", getPackageName()));
                    music_cover.setVisibility(View.VISIBLE);



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
