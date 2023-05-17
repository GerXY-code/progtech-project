package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goToRegisterPage();

    }

    /*
    public void setMyName(){
        TextView tvName = (TextView)findViewById(R.id.textView1);
        tvName.setText("Hello Gergő!");
    }*/

    public void goToRegisterPage(){
        Button btn = (Button)findViewById(R.id.register_navigate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }



}