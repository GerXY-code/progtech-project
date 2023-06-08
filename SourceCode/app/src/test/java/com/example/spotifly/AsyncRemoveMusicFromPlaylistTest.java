package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AsyncRemoveMusicFromPlaylistTest {

    Integer musicID = 1;
    String username = "geri";
    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();
        Integer countResult = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            statement.executeUpdate(" DELETE FROM playlist_music WHERE playlist_id = " +
                    "(SELECT playlist_id FROM user_playlist JOIN users ON user_playlist.user_id = users.id " +
                    "WHERE username = ('"+username+"')) AND music_id = ('"+musicID+"');");
            ResultSet resultSet = statement2.executeQuery("SELECT COUNT(id) FROM playlist_music JOIN user_playlist ON " +
                    "playlist_music.playlist_id = user_playlist.playlist_id WHERE " +
                    "user_playlist.user_id = (SELECT * FROM users WHERE username = 'geri') WHERE music_id = 1");


            assertEquals(Optional.of(0),countResult);


        } catch (Exception e) {

            String error = e.toString();
            Log.i("errr", error);

        }
    }
}