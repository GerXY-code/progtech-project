package com.example.spotifly;


import android.os.AsyncTask;
import android.util.Log;

import com.example.spotifly.ConnectionInfo;
import com.example.spotifly.RegisterActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class CreatePlaylistAsync extends AsyncTask {


    String records = "", error = "";

    String playListName = "";

    Integer playListActualID;
    Integer userID;
    public CreatePlaylistAsync(String playListName,Integer userID){
        this.playListName = playListName;
        this.userID = userID;
    }


    @Override
    protected Object doInBackground(Object[] objects) {

        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlists(name) VALUES('"+playListName+"')");
            ResultSet resultSet = statement2.executeQuery("SELECT id FROM playlists WHERE name=('"+playListName+"')");
            while(resultSet.next()) {
                playListActualID = Integer.valueOf(resultSet.getString(1));


            }
            Log.d("actualID", playListActualID.toString());
            statement3.executeUpdate("INSERT INTO user_playlist(user_id,playlist_id) VALUES('"+userID+"','"+playListActualID+"')");



        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return null;
    }
}

