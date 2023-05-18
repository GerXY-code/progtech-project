package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    TextView text,errorText;
    public static String userName, password;

    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToRegisterPage();

        text = (TextView) findViewById(R.id.appName_txt2);
        show = (Button) findViewById(R.id.login_btn);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Async().execute();
            }
        });
    }
    /*
    public void setMyName(){
        TextView tvName = (TextView)findViewById(R.id.textView1);
        tvName.setText("Hello Gergő!");
    }*/
    public void goToRegisterPage(){
        Button btn = (Button)findViewById(R.id.register_navigate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
    
    class Async extends AsyncTask<Void, Void, Void> {
        String records = "",error="";

        @Override
        protected Void doInBackground(Void... voids) {
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.206/test", "root", "");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT username, password FROM spotifly.users WHERE username=('"+userName+"') && password=('"+password+"') ");
                while(resultSet.next()) {
                    records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
                }
                if (records==""){
                    Log.d("hiba", "sikertelen Login");
                }
            }

            catch(Exception e)
            {
                error = e.toString();
                Log.i("errr", error);
            }
            return null;
        }



        @Override
        protected void onPostExecute(Void aVoid) {

            text.setText(records);

            if(error != "")

                errorText.setText(error);

            super.onPostExecute(aVoid);

        }


    }




}