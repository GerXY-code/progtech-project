package com.example.spotifly;

public class VerifyUserInputs extends UserDecoratorBase{

    //username_f_register_input_txt
    public VerifyUserInputs(UserBase u) {
        super(u);


    }

    public String userNameVerify(String usernameParam){
        String username = usernameParam;
        return username;
    }

    public String emailVerify(String emailParam){
        String email = emailParam;
        return email;
    }

    public String passwordVerify(String passwordParam){
        String password = passwordParam;
        return passwordParam;
    }



}
