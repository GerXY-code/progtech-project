package com.example.spotifly;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AsyncGetMusics extends AsyncTask<ArrayList<String[]>, ArrayList<String[]>, ArrayList<String[]>> {



    String error = "";
    String musicName,author,duration;

    String [] musicTitles = new String[3];
    String [] musicAuthor = new String[3];
    String [] musicDuration = new String[3];

    ArrayList<String[]> musicList = new ArrayList<>();

    Integer counter = 0;





    @Override
    protected ArrayList<String[]> doInBackground(ArrayList<String[]>... arrayLists) {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.45/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM musics");
            while(resultSet.next()) {
                //records += resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getTime(3) + "\n";
                musicName = resultSet.getString(2);
                author    = resultSet.getString(3);
                duration  = resultSet.getString(4);
                musicTitles[counter] = musicName;
                musicAuthor[counter] = author;
                musicDuration[counter] = duration;
                counter++;
                musicList.add(musicTitles);
                musicList.add(musicTitles);
                musicList.add(musicTitles);

            }


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return musicList;
    }
}
