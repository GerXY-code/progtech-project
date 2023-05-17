package com.example.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        username = (EditText)findViewById(R.id.username_f_register_input_txt);
        email = (EditText)findViewById(R.id.email_f_register_input_txt);
        password = (EditText)findViewById(R.id.password_f_register_input_txt);

        //goBackFromRegisterPage();
        RegisterUser();
    }

    public void goBackFromRegisterPage(){
        Button btn = (Button)findViewById(R.id.back_from_reg_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class
                ));
            }
        });
    }

    public void RegisterUser(){
        Button btn_reg = (Button)findViewById(R.id.reg_btn);
        btn_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String UserName = username.getText().toString();
                String Email   = email.getText().toString();
                String Password = password.getText().toString();
                CurrentUser cu = new CurrentUser(UserName,Email,Password);
                RegisterUser ru = new RegisterUser(cu);
                String usernameVerified = ru.userNameVerify(cu.getUserName());
                String emailVerified = ru.emailVerify(cu.getEmail());
                String passwordVerified = ru.passwordVerify(cu.getPassword());


                Log.d("username", usernameVerified);
                Log.d("email", emailVerified);
                Log.d("password", passwordVerified);

            }

        });
    }

}
