package com.example.spotifly;

import android.util.Log;

public class Playlist {



    public void AddNewMusic(String musicName){
        Log.d("zene sikeresen hozzáadva a lejátszási listához", musicName);
    }
    public void RemoveMusicFrom(String musicName) {Log.d("zene sikeresen eltávolítva a lejátszási listából", musicName);
    }

}
