package com.example.spotifly;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AsyncAddMusicToPlaylist extends AsyncTask {
    Integer musicID;
    public AsyncAddMusicToPlaylist(Integer musicID){
        this.musicID = musicID;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlist_music(playlist_id,music_id) VALUES(1,'"+musicID+"')");


        } catch (Exception e) {

            String error = e.toString();
            Log.i("errr", error);

        }

        return null;

    }
}
