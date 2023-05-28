package com.example.spotifly;

import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayingState extends AudioPlayerState{
    @Override
    public void PlayPressed(Integer state, FloatingActionButton pauseButton, FloatingActionButton playButton) {
        //0 means media is stopped
        //1 means media is playing
        //2 means the state of the beginning
        if(state == 1){
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.INVISIBLE);
        }else if(state == 0){
            pauseButton.setVisibility(View.INVISIBLE);
            playButton.setVisibility(View.VISIBLE);
        }else if(state == 2){
            pauseButton.setVisibility(View.INVISIBLE);
            playButton.setVisibility(View.INVISIBLE);
        }
    }

}
