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

public class AsyncHaveUsersPlaylist extends AsyncTask<Integer, Integer, Integer> {


    String error = "";

    String currentUsername = MainActivity.username;

    @Override
    protected Integer doInBackground(Integer... integers) {
        Integer szam = 0;
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + ci.IPAddress + "/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM playlist WHERE id = (SELECT playlist_id FROM user_playlist WHERE user_id = (SELECT id FROM users WHERE username = '"+currentUsername+"')); ");
            while(resultSet.next()){
                szam = resultSet.getInt(1);
            }



        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }
        return szam;
    }
}