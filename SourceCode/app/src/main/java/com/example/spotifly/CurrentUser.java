package com.example.spotifly;

import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class CurrentUser extends UserBase{

    String setUserName;

    String currentUser;
    String setEmail;
    String setPassword;

   public CurrentUser(String username, String email, String password){
       setUserName = username;
       setEmail    = email;
       setPassword    = password;
   }




    @Override
    public String getUserName(){
        String username = setUserName;
        return username;
    };
    @Override
    public String getEmail(){
        String email = setEmail;
        return email;
    }
    @Override
    public String getPassword(){
        String password = setPassword;
        return password;
    }

}
