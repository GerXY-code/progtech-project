package com.example.spotifly;

public class VerifyUserInputs extends UserDecoratorBase{
    //username_f_register_input_txt
    String username,email,password;
    public VerifyUserInputs(UserBase u) {
        super(u);
        username = u.getUserName();
        email = u.getEmail();
        password = u.getPassword();

    }
    public String userNameVerify(){
        String verifiedUsername = username;
        return verifiedUsername;
    }
    public String emailVerify(){
        String verifiedEmail = email;
        return verifiedEmail;
    }
    public String passwordVerify(){
        String verifiedPassword = password;
        return verifiedPassword;
    }
}
