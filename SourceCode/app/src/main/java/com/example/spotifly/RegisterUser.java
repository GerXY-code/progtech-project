package com.example.spotifly;

import android.util.Log;
import android.widget.TextView;

public class RegisterUser extends UserDecoratorBase{

    //username_f_register_input_txt
    public RegisterUser(UserBase u) {
        super(u);

    }

    @Override
    public String getUserName(){
        TextView text = (TextView) findViewById(R.id.username_f_register_input_txt);
        String username = text.getText().toString();
        return username;

    };
    @Override
    public String getEmail(){
        String email = (String)findViewById(R.id.email_f_register_input_txt).toString();
        return email;
    }
    @Override
    public String getPassword(){
        String password = (String)findViewById(R.id.password_f_register_input_txt).toString();
        return password;
    }
    @Override
    public void registration(){
        String username = getUserName();


        Log.d("username", username);
    }



}
