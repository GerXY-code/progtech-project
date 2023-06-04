package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView text,errorText;
    EditText UserName, Password;
    public static String username,password, convertedHashedPassword;


    Button show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToRegisterPage();

        UserName = (EditText) findViewById(R.id.username_input_txt);
        Password = (EditText) findViewById(R.id.password_input_txt);

        login();


    }
    public void goToRegisterPage(){
        Button btn = (Button)findViewById(R.id.register_navigate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }
    public void login(){
        Button btn = (Button)findViewById(R.id.login_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = UserName.getText().toString();
                password = Password.getText().toString();
                Integer hashedPassword = password.hashCode();
                convertedHashedPassword = hashedPassword.toString();
                //new Async().execute();
                Async task = new Async();
                try {
                    Boolean result = task.execute().get();
                    if (result){
                        //startActivity(new Intent(MainActivity.this, LandingPageActivity.class));
                        goToHomePage();
                    }
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void goToHomePage(){
        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
    }

}
class Async extends AsyncTask<Boolean,Boolean, Boolean> {
    String username=MainActivity.username;
    String password = MainActivity.convertedHashedPassword;
    String records = "",error="";

    MainActivity mainActivity = new MainActivity();


    @Override
    protected Boolean doInBackground(Boolean... Boolean) {

        ConnectionInfo ci = new ConnectionInfo();

        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT username, password FROM spotifly.users WHERE username=('"+username+"') AND password=('"+password+"')");
            while(resultSet.next()) {
                records += resultSet.getString(1) + " " + resultSet.getString(2) + "\n";
            }
            if (records==""){
                Log.d("hiba", "Sikertelen Login");
                return false;
            }
            else{
                Log.d("siker","Sikeres login");
                return true;
            }
        }

        catch(Exception e)
        {
            error = e.toString();
            Log.i("errr", error);
        }
        return false;
    }

}