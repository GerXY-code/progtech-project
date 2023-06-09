package com.example.spotifly;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password;
    Spinner sub_spinner;
    public static Integer selectedTier;

    private static final String[] paths = {"VIP", "Ultimate", "Normal"};


    public static String usernameVerified,emailVerified,passwordVerified,hashedPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);
        username = (EditText)findViewById(R.id.username_f_register_input_txt);
        email = (EditText)findViewById(R.id.email_f_register_input_txt);
        password = (EditText)findViewById(R.id.password_f_register_input_txt);

        sub_spinner = (Spinner)findViewById(R.id.sub_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_spinner_item,paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sub_spinner.setAdapter(adapter);



        goBackFromRegisterPage();
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
            public void onClick(View v) {
                String UserName = username.getText().toString();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                CurrentUser cu = new CurrentUser(UserName, Email, Password);
                VerifyUserInputs ru = new VerifyUserInputs(cu);
                usernameVerified = ru.userNameVerify();
                emailVerified = ru.emailVerify();
                passwordVerified = ru.passwordVerify();

                PasswordHashing pwH = new PasswordHashing(cu);
                hashedPassword = pwH.hashThePassword(passwordVerified);

                Log.d("username", usernameVerified);
                Log.d("email", emailVerified);


                new AsyncRegister().execute();


                goToHomePage();


            }

            public void goToHomePage(){
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }

        });
    }

}


 class AsyncRegister extends AsyncTask<String, String, String> {
    String records = "", error = "";
    String username = RegisterActivity.usernameVerified;
     String email = RegisterActivity.emailVerified;
     String password = RegisterActivity.hashedPassword;


    @Override
    protected String doInBackground(String... strings) {
        ConnectionInfo ci = new ConnectionInfo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO users(username,email,password) VALUES('"+username+"','"+email+"','"+password+"')");
        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }
        return null;
    }
    @Override
    protected void onPostExecute(String msg) {
        if (error != "")
            super.onPostExecute(msg);
    }
}

class AsyncRegisterSub extends AsyncTask<String, String, String> {
    String records = "", error = "";
    String username = RegisterActivity.usernameVerified;
    Integer selectedTier = RegisterActivity.selectedTier;
    @Override
    protected String doInBackground(String... strings) {
        ConnectionInfo ci = new ConnectionInfo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://"+ci.IPAddress+"/spotifly", "root", "");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO user_sub(user_id,sub_id) VALUES((SELECT id FROM users WHERE username = '"+username+"'),'"+selectedTier+"')");
        } catch (Exception e) {

            error = e.toString();
            Log.i("errr", error);

        }
        return null;
    }
    @Override
    protected void onPostExecute(String msg) {
        if (error != "")
            super.onPostExecute(msg);
    }
}



