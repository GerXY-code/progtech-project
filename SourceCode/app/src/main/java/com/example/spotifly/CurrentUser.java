package com.example.spotifly;

public class CurrentUser extends UserBase{

    @Override
    public String getUserName(){
        String username = (String)findViewById(R.id.username_f_register_input_txt).toString();
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

    }
}
