package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AsyncGetMusicsTest {

    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();
        Integer countOfMusics = 0;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(id) FROM musics");
            while(resultSet.next()) {
                 countOfMusics = resultSet.getInt(1);



            }

            assertNotEquals(Optional.of(0),countOfMusics);


        } catch (Exception e) {



        }

    }
}