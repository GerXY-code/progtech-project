package com.example.spotifly;

import android.content.Intent;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class Playlist {



    public void AddNewMusic(Integer musicID){
        Log.d("Music ID for add", musicID.toString());
    }
    public void RemoveMusicFrom(String musicName) {Log.d("zene sikeresen eltávolítva a lejátszási listából", musicName);
    }

    public void CreateNewPlaylist(String playListName,Integer userID) throws ExecutionException, InterruptedException {
            //Log.d("playlistName", playListName);
            GetLoggedUserAsync task = new GetLoggedUserAsync();
            try {
                userID = task.execute().get();
                //Log.d("userID", userID.toString());

            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            CreatePlaylistAsync cpA = new CreatePlaylistAsync(playListName,userID);
            cpA.execute().get();
    }

    public void DeletePlaylist(Integer userID, Integer playlistID){

    }

}
