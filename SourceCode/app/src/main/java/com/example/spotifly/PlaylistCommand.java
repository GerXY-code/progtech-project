package com.example.spotifly;

public class PlaylistCommand implements Command{

    Playlist pl;
    PlaylistAction action;

    String musicName;

    public PlaylistCommand(Playlist p, PlaylistAction action, String musicName){
        this.pl = p;
        this.action = action;
        this.musicName = musicName;
    }

    @Override
    public void call() {
        switch (action){
            case AddNewMusic: pl.AddNewMusic(musicName);break;
            case RemoveMusicFrom: pl.RemoveMusicFrom(musicName);break;
            default: break;
        }
    }
}
