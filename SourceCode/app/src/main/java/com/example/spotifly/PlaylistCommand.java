package com.example.spotifly;

import java.util.concurrent.ExecutionException;

public class PlaylistCommand implements Command{

    Playlist pl;
    PlaylistAction action;

    String musicName,playlistName;

    Integer userID,playlistID,musicID;



    public PlaylistCommand(Playlist p, PlaylistAction action, Integer musicID, String playlistName){
        this.pl = p;
        this.action = action;
        this.playlistName = playlistName;
        this.musicID = musicID;

    }
    public PlaylistCommand(Playlist p, PlaylistAction action, Integer musicID){
        this.pl = p;
        this.action = action;
        this.musicID = musicID;
    }

    @Override
    public void call() throws ExecutionException, InterruptedException {
        switch (action){
            case AddNewMusic: pl.AddNewMusic(musicID);break;
            case RemoveMusicFrom: pl.RemoveMusicFrom(musicID);break;
            case CreatePlaylist: pl.CreateNewPlaylist(playlistName,userID);break;
            case DeletePlaylist: pl.DeletePlaylist(userID,playlistID);
            default: break;
        }
    }
}
