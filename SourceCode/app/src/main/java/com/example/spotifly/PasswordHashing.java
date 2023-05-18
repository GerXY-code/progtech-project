package com.example.spotifly;

public class PasswordHashing extends UserDecoratorBase{

    public PasswordHashing(UserBase u) {
        super(u);
    }

    public String hashThePassword(String passwordForHashing){
        Integer hashedPassword = passwordForHashing.hashCode();
                return hashedPassword.toString();
    }

}
