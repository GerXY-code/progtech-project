package com.example.spotifly;

import java.util.concurrent.ExecutionException;

public class PlaylistCommand implements Command{

    Playlist pl;
    PlaylistAction action;

    String musicName,playlistName;

    Integer userID,playlistID;



    public PlaylistCommand(Playlist p, PlaylistAction action, String musicName, String playlistName){
        this.pl = p;
        this.action = action;
        this.musicName = musicName;
        this.playlistName = playlistName;
    }

    @Override
    public void call() throws ExecutionException, InterruptedException {
        switch (action){
            case AddNewMusic: pl.AddNewMusic(musicName);break;
            case RemoveMusicFrom: pl.RemoveMusicFrom(musicName);break;
            case CreatePlaylist: pl.CreateNewPlaylist(playlistName,userID);break;
            case DeletePlaylist: pl.DeletePlaylist(userID,playlistID);
            default: break;
        }
    }
}
