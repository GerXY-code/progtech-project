package com.example.spotifly;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Music {

    Context ctx;
    MediaPlayer mp;
    Integer stoppedAt;
    public Music(Context ctx){
        this.ctx = ctx;
    }

    public void Start(String musicID){
      Resources res = ctx.getResources();
      int soundId = res.getIdentifier(musicID, "raw", ctx.getPackageName());
      mp = MediaPlayer.create(ctx,soundId);
      mp.start();
    }
    public void Pause(){
        mp.pause();
        stoppedAt = mp.getCurrentPosition();
    }
    public void SeekToStart(){
        mp.start();
        mp.seekTo(stoppedAt);
    }
    public void Stop(){
    }
    public void Replay(String musicID){
        mp.reset();
        Start(musicID);
    }
}
