package com.example.spotifly;

import static org.junit.Assert.*;

import android.content.res.Resources;
import android.media.MediaPlayer;

import org.junit.Test;

public class MusicTest {

    @Test
    public void start() {
        Resources res = ctx.getResources();
        int soundId = res.getIdentifier(musicID, "raw", ctx.getPackageName());
        mp = MediaPlayer.create(ctx,soundId);
        mp.start();
    }
}