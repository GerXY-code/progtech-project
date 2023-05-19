package com.example.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class HomePageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Playlist p = new Playlist();
        PlaylistCommand addToPlaylistCommand      = new PlaylistCommand(p,Action.AddNewMusic,"armanen");
        PlaylistCommand removeFromPlaylistCommand = new PlaylistCommand(p,Action.RemoveMusicFrom, "armanen");
        addToPlaylistCommand.call();
        removeFromPlaylistCommand.call();


    }






}
