package com.example.spotifly;

public abstract class UserDecoratorBase extends UserBase {

    private UserBase user;
    public UserDecoratorBase(UserBase u) {user = u;}

    public String Username;
    public String Email;
    public String Password;


}
