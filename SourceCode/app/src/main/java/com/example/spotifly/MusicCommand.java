package com.example.spotifly;

public class MusicCommand implements Command{

    String musicName;
    MusicAction action;

    Music m;


    public MusicCommand(Music m, MusicAction action, String musicName){
        this.m = m;
        this.musicName = musicName;
        this.action = action;
    }

    @Override
    public void call() {
        switch (action){
            case Start: m.Start(musicName);break;
            case Stop: m.Stop(musicName);break;
            case Pause: m.Pause(musicName);break;
            default: break;
        }
    }


}
