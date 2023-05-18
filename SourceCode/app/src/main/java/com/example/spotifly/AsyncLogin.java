package com.example.spotifly;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AsyncLogin extends AsyncTask<String, String, String> {
    String records = "", error = "";
    String username = RegisterActivity.usernameVerified;
    String email = RegisterActivity.emailVerified;
    String password = RegisterActivity.hashedPassword;


    @Override
    protected String doInBackground(String... strings) {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.206/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(username,email,password) VALUES('"+username+"','"+email+"','"+password+"')");


        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }

        return null;

    }


    @Override
    protected void onPostExecute(String msg) {


        if (error != "")


            super.onPostExecute(msg);

    }
}
