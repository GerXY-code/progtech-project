package com.example.spotifly;

import android.os.AsyncTask;
import android.util.Log;

import androidx.loader.content.AsyncTaskLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

class CheckTheMusicStateAsync extends AsyncTask<ArrayList<Integer>,ArrayList<Integer>, ArrayList<Integer>> {


    String username=MainActivity.username;
    String records = "",error="";


    @Override
    protected ArrayList<Integer> doInBackground(ArrayList<Integer>... arrayLists) {
        ArrayList<Integer> musicListsOfAlreadyAdded = new ArrayList<>();
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT music_id FROM playlist_music WHERE playlist_id =(SELECT playlist_id FROM user_playlist WHERE user_id=(SELECT id FROM users WHERE username = ('"+username+"')));");
            while(resultSet.next()) {
                Integer musicId = resultSet.getInt(1);
                musicListsOfAlreadyAdded.add(musicId);
                Log.d("loggedMusicId", musicId.toString());


            }


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return musicListsOfAlreadyAdded;
    }
}
