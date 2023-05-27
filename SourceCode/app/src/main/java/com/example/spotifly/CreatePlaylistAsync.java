package com.example.spotifly;


import android.os.AsyncTask;
import android.util.Log;

import com.example.spotifly.ConnectionInfo;
import com.example.spotifly.RegisterActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class CreatePlaylistAsync extends AsyncTask {


    String records = "", error = "";

    String playListName = "";
    public CreatePlaylistAsync(String playListName){
        this.playListName = playListName;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlists(name) VALUES('"+playListName+"')");


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return null;
    }
}

