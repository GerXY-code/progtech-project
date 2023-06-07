package com.example.spotifly;

import static org.junit.Assert.*;

import android.content.Context;
import android.media.MediaPlayer;

import org.junit.Test;

public class MusicTest {


    Context ctx;
    MediaPlayer mp = MediaPlayer.create(ctx,R.raw.one);

    public void start(){


        mp.start();

    }

    @Test
    public void pause() {
        start();
        mp.pause();
        assertEquals(true,     mp.isPlaying());

    }
}