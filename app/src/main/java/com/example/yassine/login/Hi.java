package com.example.yassine.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Hi extends AppCompatActivity {
    String name, password, email, Err;
    TextView nameTV, emailTV, passwordTV, err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);

        nameTV = (TextView) findViewById(R.id.home_name);
        emailTV = (TextView) findViewById(R.id.home_email);
        passwordTV = (TextView) findViewById(R.id.home_password);
        err = (TextView) findViewById(R.id.err);

        name = getIntent().getStringExtra("name");
        password = getIntent().getStringExtra("password");
        email = getIntent().getStringExtra("email");
        Err = getIntent().getStringExtra("err");

        nameTV.setText("Welcome "+name);
        passwordTV.setText("Your password is "+password);
        emailTV.setText("Your email is "+email);
        err.setText(Err);
    }
}
