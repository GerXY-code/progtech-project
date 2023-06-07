package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AsyncAddMusicToPlaylistTest {


    String playlistName = "testplaylist";
    Integer musicID = 4;
    String playlistId;
    String rightPlaylistId;
    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();
            Statement statement4 = connection.createStatement();
            statement.executeUpdate("INSERT INTO playlist(name) VALUES('"+playlistName+"')");
            statement2.executeUpdate("INSERT INTO playlist_music(playlist_id,music_id) VALUES((SELECT id FROM playlist " +
                    "WHERE name = '"+playlistName+"'),'"+musicID+"'); ");
            ResultSet resultSet = statement3.executeQuery("SELECT playlist_id FROM playlist_music WHERE playlist_id = " +
                    "(SELECT id FROM playlist WHERE name = '"+playlistName+"')");
            ResultSet resultSet2 = statement4.executeQuery("SELECT id FROM playlist WHERE name = '"+playlistName+"')");
            while(resultSet.next()) {
                playlistId = String.valueOf(resultSet.getString(1));
            }
            while(resultSet.next()) {
                rightPlaylistId = String.valueOf(resultSet2.getString(1));
            }
            assertEquals(playlistId,rightPlaylistId);
        } catch (Exception e) {
        }
    }
}