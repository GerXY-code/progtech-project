package com.example.spotifly;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AudioPlayer {

    public Integer auidoPlayerState;
    FloatingActionButton pauseButton,playButton;
    public AudioPlayer(Integer audioPlayerState, FloatingActionButton pauseButton,FloatingActionButton playButton){
        this.auidoPlayerState = audioPlayerState;
        this.pauseButton = pauseButton;
        this.playButton = playButton;
    }
    public void audioPlayerSetState(){
        AudioPlayerState au = new AudioPlayingState();
        au.PlayPressed(auidoPlayerState,pauseButton,playButton);
    }


}
