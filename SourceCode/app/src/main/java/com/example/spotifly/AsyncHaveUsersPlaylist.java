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
        String szamStringben;
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://" + ci.IPAddress + "/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM playlist WHERE id = (SELECT playlist_id FROM user_playlist WHERE user_id = (SELECT id FROM users WHERE username = '"+currentUsername+"')); ");
            szamStringben = resultSet.toString();
            szam = Integer.parseInt(szamStringben);
            Log.d("querry EREMÉNYE", szam.toString());


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);
            Log.d("erdemennjhojasijdiasdjsndandasdasndosa", "");

        }
        return szam;
    }
}