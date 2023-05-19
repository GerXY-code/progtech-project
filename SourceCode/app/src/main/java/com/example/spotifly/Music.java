package com.example.spotifly;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

public class Music {

    Context ctx;
    public Music(Context ctx){
        this.ctx = ctx;
    }

    public void Start(String musicName){

      MediaPlayer  mp = MediaPlayer.create(ctx,R.raw.armanen);
      mp.start();
    }
    public void Stop(String musicName){
    }

    public void Pause(String musicName){

    }
}
