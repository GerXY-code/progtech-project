package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setMyName();

    }

    public void setMyName(){
        TextView tvName = (TextView)findViewById(R.id.textView1);
        tvName.setText("Hello Gergő!");
    }
}