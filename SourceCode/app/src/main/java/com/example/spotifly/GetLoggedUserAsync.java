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

public class GetLoggedUserAsync extends AsyncTask<Integer, Integer, Integer> {



    String error = "";
    Integer loggedUserID;



    @Override
    protected Integer doInBackground(Integer... integers) {
        ConnectionInfo ci = new ConnectionInfo();
        String loggedUserName = MainActivity.username;

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM users WHERE username='"+loggedUserName+"'");
            while(resultSet.next()) {
                loggedUserID = Integer.valueOf(resultSet.getString(1));


            }


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return loggedUserID;
    }

}
