package com.example.spotifly;

import static org.junit.Assert.*;

import android.util.Log;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;

public class AsyncRegisterTest {

    String username;
    String testUsername = "testuser";
    @Test
    public void doInBackground() {
        ConnectionInfo ci = new ConnectionInfo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            Statement statement2 = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(username,email,password) VALUES('"+testUsername+"','test@test.com','test')");
            ResultSet resultSet = statement2.executeQuery("SELECT name FROM users WHERE name='"+testUsername+"'");
            while(resultSet.next()) {
                username = String.valueOf(resultSet.getString(1));
            }
            Log.d("actualUsername", username);
            assertEquals(testUsername,username);

        } catch (Exception e) {
        }
    }

    @Test
    public void onPostExecute() {
    }
}