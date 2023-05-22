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

public class AsyncGetMusics extends AsyncTask<ArrayList<CurrentMusic>, ArrayList<CurrentMusic>, ArrayList<CurrentMusic>> {



    String error = "";


    @Override
    protected ArrayList<CurrentMusic> doInBackground(ArrayList<CurrentMusic>... arrayLists) {
        ArrayList<CurrentMusic> musicList = new ArrayList<>();
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM musics");
            while(resultSet.next()) {
                CurrentMusic cm = new CurrentMusic(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5));
                musicList.add(cm);


            }


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return musicList;
    }
}
