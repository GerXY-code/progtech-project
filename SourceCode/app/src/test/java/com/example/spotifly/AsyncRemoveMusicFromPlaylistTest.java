package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AsyncRemoveMusicFromPlaylistTest {

    Integer musicID = 1;
    String username = "geri";
    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate(" DELETE FROM playlist_music WHERE playlist_id = (SELECT playlist_id FROM user_playlist JOIN users ON user_playlist.user_id = users.id WHERE username = ('"+username+"')) AND music_id = ('"+musicID+"');");


        } catch (Exception e) {

            String error = e.toString();
            Log.i("errr", error);

        }
    }
}