package com.example.spotifly;

public abstract class UserDecoratorBase extends UserBase {

    private UserBase user;
    public UserDecoratorBase(UserBase u) {user = u;}

    @Override
    public String getUserName(){
        return user.Username;
    };

    @Override
    public String getEmail(){
        return user.Email;
    };

    @Override
    public String  getPassword(){
        return user.Password;
    };

    @Override
    public void registration(){

    };

}
