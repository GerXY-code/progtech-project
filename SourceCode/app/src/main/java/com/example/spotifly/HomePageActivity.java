package com.example.spotifly;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class HomePageActivity extends AppCompatActivity {

    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        testForMusicPlaying();


        Playlist p = new Playlist();
        PlaylistCommand pc = new PlaylistCommand(p,Action.AddNewMusic,"armanen");
        pc.call();


    }


    public void testForMusicPlaying(){
        Button play = (Button)findViewById(R.id.play_btn);
        mp = MediaPlayer.create(this,R.raw.armanen);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

            }
        });
    }





}
