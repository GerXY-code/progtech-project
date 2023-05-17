package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

public abstract class UserBase extends AppCompatActivity {


    public abstract String getUserName();
    public abstract String getEmail();
    public abstract String getPassword();

    public abstract void registration();

    public  String Username;
    public  String Email;
    public  String Password;


}
