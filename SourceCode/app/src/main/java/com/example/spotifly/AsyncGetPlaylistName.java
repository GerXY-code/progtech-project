package com.example.spotifly;

import android.os.AsyncTask;
import android.util.Log;

import com.example.spotifly.ConnectionInfo;
import com.example.spotifly.CurrentMusic;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsyncGetPlaylistName extends AsyncTask<String, String, String> {



    String error = "";

    String currentUsername = MainActivity.username;

    @Override
    protected String doInBackground(String ... strings) {
        String playlistNeve = "";
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT name FROM playlist WHERE id = (SELECT playlist_id FROM user_playlist WHERE user_id = (SELECT id FROM users WHERE username = '"+currentUsername+"'))");
            playlistNeve = resultSet.toString();


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }
        return playlistNeve;
    }
}
