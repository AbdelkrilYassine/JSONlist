package com.example.yassine.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Hi extends AppCompatActivity {
    String name, password;




    
    String USERNAME,PASSWORD,EMAIL,NAME;

    TextView usernameTV, emailTV, passwordTV, nameTV,errr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi);

        name = getIntent().getStringExtra("usr");

       password = getIntent().getStringExtra("pwd");



        usernameTV = (TextView) findViewById(R.id.home_name);
        emailTV = (TextView) findViewById(R.id.home_email);
        passwordTV = (TextView) findViewById(R.id.home_password);
        nameTV=(TextView) findViewById(R.id.home_ns);
        errr = (TextView) findViewById(R.id.err);
       BackGroundProfile b = new BackGroundProfile();
        b.execute(name,password);



    }

    class BackGroundProfile extends AsyncTask<String, String, String> {



        @Override
        protected String doInBackground(String... params) {



             name = params[0];
             password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://api2.randon.ili-studios.tn/afficheprofile.php?username="+name+"&password="+password);
                String urlParams = "";

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                USERNAME = user_data.getString("Username");
                PASSWORD = user_data.getString("Password");
                EMAIL = user_data.getString("Email");
                NAME=user_data.getString("Name");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            usernameTV.setText("Welcome "+USERNAME);
            passwordTV.setText("Your password is "+PASSWORD);
            emailTV.setText("Your email is "+EMAIL);
            nameTV.setText("Your name "+NAME);

        }
    }
}
