package com.example.spotifly;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
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
                CurrentUser cu = new CurrentUser();
                RegisterUser ru = new RegisterUser(cu);
                ru.registration();

            }

        });
    }

}
