package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AsyncTest {

    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();
        Integer records = 0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT COUNT(id) FROM spotifly.users WHERE username='test' AND password='test'");
            while(resultSet.next()) {
                records = resultSet.getInt(1);
            }

            assertNotEquals(Optional.of(0),records);

        }

        catch(Exception e)
        {

        }

    }
}