package com.example.yassine.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class reg extends Activity {

    EditText name, password, email,namee,birthdate,sexe;
    String Name, Password, Email,Namee,Birthdate,Sexe;
    Context ctx=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        name = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        namee = (EditText) findViewById(R.id.register_namee);
        birthdate = (EditText) findViewById(R.id.register_birthdate);
        sexe = (EditText) findViewById(R.id.register_sexe);
    }

    public void register_register(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        Email = email.getText().toString();
        Namee = namee.getText().toString();
        Birthdate = birthdate.getText().toString();
        Sexe = sexe.getText().toString();

        BackGround b = new BackGround();
        b.execute(Name, Password, Email,Namee,Birthdate,Sexe);
    }

    class BackGround extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... params) {


            String name = params[0];
            String password = params[1];
            String email = params[2];
            String namee = params[3];
            String birthdate = params[4];
            String sexe = params[5];

            String data="";
            int tmp;

            try {
                URL url = new URL("http://api2.randon.ili-studios.tn/Signme.php?username="+name+"&password="+password+"&email="+email+"&name="+namee+"&birthdate="+birthdate+"&gender="+sexe);
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
            if(s.equals("Thanks for Sign up!")){
                Toast.makeText(reg.this,"Data saved successfully!", Toast.LENGTH_LONG).show();
            }
            else{                Toast.makeText(reg.this,s, Toast.LENGTH_LONG).show();
            }
        }
    }

}