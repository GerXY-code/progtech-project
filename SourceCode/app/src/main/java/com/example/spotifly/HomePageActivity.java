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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class HomePageActivity extends AppCompatActivity {

    ListView musicList;
    FloatingActionButton playButton,pauseButton,addToPlaylistButton,removeFromPlaylistButton;

    ImageView music_cover;

    Integer length,currentMusicID, playlistCount, tierId, musicInPlaylist;
    String Position;
    ArrayList<CurrentMusic> musics = new ArrayList<>();

    ArrayList<Integer> musicsAdded = new ArrayList<>();

    TextView musicTitle, music_author;
    Boolean hasThisMusic;
    Button playlist_btn;
    Boolean playingMusic = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        musicList = (ListView)findViewById(R.id.music_list);
        playButton = (FloatingActionButton) findViewById(R.id.action_start);
        pauseButton = (FloatingActionButton) findViewById(R.id.action_pause);
        music_cover = (ImageView)findViewById(R.id.music_cover);
        music_author = (TextView)findViewById(R.id.music_author);
        addToPlaylistButton = (FloatingActionButton)findViewById(R.id.add_to_playlist_btn);
        music_cover.setVisibility(View.INVISIBLE);

        AudioPlayer au = new AudioPlayer(2,pauseButton,playButton);
        au.audioPlayerSetState();

        addToPlaylistButton.setVisibility(View.INVISIBLE);
        removeFromPlaylistButton = (FloatingActionButton)findViewById(R.id.remove_btn_from_playlist);
        removeFromPlaylistButton.setVisibility(View.INVISIBLE);

        playlist_btn = (Button)findViewById(R.id.playlist_btn);

        musicTitle = (TextView)findViewById(R.id.music_title);
        getMusics();
        checkTheAddedMusics();
        HavePlaylist();
        WhichTier();
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
    public void WhichTier(){
        AsyncGetTier task3 = new AsyncGetTier();
        try {
            tierId = task3.execute().get();
            Log.d("tier:", tierId.toString());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void HavePlaylist(){
        AsyncHaveUsersPlaylist task2 = new AsyncHaveUsersPlaylist();
        try {
            playlistCount = task2.execute().get();
            Log.d("számolás eredmenye", playlistCount.toString());
            //Log.d("musicsAddedAlready", musicsAdded.get(0).toString());

        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
                hasThisMusic = false;
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

                    for (int i = 0; i < musicsAdded.size(); i++) {
                        //Log.d("elements", musicsAdded.get(i).toString());
                        if(musics.get(position).id == musicsAdded.get(i)) {
                            hasThisMusic = true;
                        }
                    }
                    if(hasThisMusic){
                        removeFromPlaylistButton.setVisibility(View.VISIBLE);
                        addToPlaylistButton.setVisibility(View.INVISIBLE);
                    }else{
                        removeFromPlaylistButton.setVisibility(View.INVISIBLE);
                        addToPlaylistButton.setVisibility(View.VISIBLE);
                    }
                    currentMusicID = musics.get(position).id;
                    playingMusic = true;
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

                    for (int i = 0; i < musicsAdded.size(); i++) {
                        //Log.d("elements", musicsAdded.get(i).toString());
                        if(musics.get(position).id == musicsAdded.get(i)) {
                            hasThisMusic = true;
                        }


                    }
                    if(hasThisMusic){
                        removeFromPlaylistButton.setVisibility(View.VISIBLE);
                        addToPlaylistButton.setVisibility(View.INVISIBLE);
                    }else{
                        removeFromPlaylistButton.setVisibility(View.INVISIBLE);
                        addToPlaylistButton.setVisibility(View.VISIBLE);
                    }

                    playingMusic = true;
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
                playingMusic = true;

            }
        });
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicCommand startMusic = new MusicCommand(m,MusicAction.Pause,Position);
                AudioPlayer au = new AudioPlayer(0,pauseButton,playButton);
                au.audioPlayerSetState();
                startMusic.call();
                playingMusic = false;

            }
        });

        addToPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playlistCount != 0){

                    AsyncGetMusicNumberInPlaylist task4 = new AsyncGetMusicNumberInPlaylist();
                    try {
                        musicInPlaylist = task4.execute().get();
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    Integer maxMusicInPlaylist;
                    Log.d("MUSIC IN:", musicInPlaylist.toString());
                    if(tierId == 1)
                        maxMusicInPlaylist = 5;
                    else if(tierId == 2)
                        maxMusicInPlaylist = 7;
                    else
                        maxMusicInPlaylist = 3;

                    Log.d("MAX MUSIC:", maxMusicInPlaylist.toString());
                    if (maxMusicInPlaylist > musicInPlaylist){

                        Playlist p = new Playlist();
                        PlaylistCommand addToPlaylist = new PlaylistCommand(p,PlaylistAction.AddNewMusic,currentMusicID);
                        try {
                            addToPlaylist.call();
                            addToPlaylistButton.setVisibility(View.INVISIBLE);
                            removeFromPlaylistButton.setVisibility(View.VISIBLE);
                        } catch (ExecutionException e) {
                            throw new RuntimeException(e);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Your subscription is not allow to add more music!", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Create your playlist first!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        removeFromPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Playlist p = new Playlist();
                PlaylistCommand removeFromPlaylist = new PlaylistCommand(p,PlaylistAction.RemoveMusicFrom,currentMusicID);
                try {
                    removeFromPlaylist.call();
                    addToPlaylistButton.setVisibility(View.VISIBLE);
                    removeFromPlaylistButton.setVisibility(View.INVISIBLE);
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
                if(playingMusic){
                    MusicCommand startMusic = new MusicCommand(m,MusicAction.Pause,Position);
                    AudioPlayer au = new AudioPlayer(0,pauseButton,playButton);
                    au.audioPlayerSetState();
                    startMusic.call();
                }
                if (playlistCount != 0){
                    startActivity(new Intent(HomePageActivity.this, My_playlist_activity.class));
                }
                else {
                    startActivity(new Intent(HomePageActivity.this, LandingPageActivity.class));
                }
            }
        });



    }











}
