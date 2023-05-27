package com.example.spotifly;

import android.util.Log;

import java.util.concurrent.ExecutionException;

public class Playlist {



    public void AddNewMusic(String musicName){
        Log.d("zene sikeresen hozzáadva a lejátszási listához", musicName);
    }
    public void RemoveMusicFrom(String musicName) {Log.d("zene sikeresen eltávolítva a lejátszási listából", musicName);
    }

    public void CreateNewPlaylist(String playListName,Integer userID) throws ExecutionException, InterruptedException {
            //Log.d("playlistName", playListName);
            CreatePlaylistAsync cpA = new CreatePlaylistAsync(playListName);
            cpA.execute().get();
    }

    public void DeletePlaylist(Integer userID, Integer playlistID){

    }

}
