package com.example.spotifly;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public abstract class AudioPlayerState {
    public abstract void PlayPressed(Integer state, FloatingActionButton pauseButton,FloatingActionButton playButton);
}
