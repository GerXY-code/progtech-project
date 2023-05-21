package com.example.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class HomePageActivity extends AppCompatActivity {

    ListView musicList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        musicList = (ListView)findViewById(R.id.music_list);
        Playlist p = new Playlist();
        PlaylistCommand addToPlaylistCommand      = new PlaylistCommand(p, PlaylistAction.AddNewMusic,"armanen");
        PlaylistCommand removeFromPlaylistCommand = new PlaylistCommand(p, PlaylistAction.RemoveMusicFrom, "armanen");
        addToPlaylistCommand.call();
        removeFromPlaylistCommand.call();

        getMusics();


    }


    protected void getMusics(){

        ArrayList<String[]> musics = new ArrayList<>();
        AsyncGetMusics task = new AsyncGetMusics();
        try {
            musics = task.execute().get();
            MusicBaseAdapter mbA = new MusicBaseAdapter(getApplicationContext(),musics);
            musicList.setAdapter(mbA);


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Music m = new Music(this);
        MusicCommand startMusic = new MusicCommand(m,MusicAction.Start,musics.get(0)[0]);


    }











}
