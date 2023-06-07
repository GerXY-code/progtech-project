package com.example.spotifly;

import static org.junit.Assert.*;

import android.content.Context;
import android.media.MediaPlayer;

import org.junit.Test;

public class MusicTest {


    Context ctx;
    MediaPlayer mp = MediaPlayer.create(ctx,R.raw.one);

    @Test
    public void start(){
        mp.start();
        assertEquals(true, mp.isPlaying());
    }

    @Test
    public void pause() {
        start();
        mp.pause();
        assertEquals(false,  mp.isPlaying());

    }
}