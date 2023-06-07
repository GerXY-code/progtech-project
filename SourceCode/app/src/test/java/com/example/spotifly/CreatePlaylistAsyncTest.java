package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class CreatePlaylistAsyncTest {

    Integer playListActualID;

    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlist(id,name) VALUES(10,'testPlaylist')");
            ResultSet resultSet = statement2.executeQuery("SELECT id FROM playlist WHERE name='testPlaylist'");
            while(resultSet.next()) {
                playListActualID = Integer.valueOf(resultSet.getString(1));


            }
            Log.d("actualID", playListActualID.toString());
            //statement3.executeUpdate("INSERT INTO user_playlist(user_id,playlist_id) VALUES('','')");
            assertEquals(Optional.of(10),playListActualID);



        } catch (Exception e) {



        }


    }
}