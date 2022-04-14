package com.example.last;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class signup extends AppCompatActivity {
    Toolbar ret;
    TextView email,pass,user,tel;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ret = (Toolbar) findViewById(R.id.toolbar1);
        ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(),login.class);
                startActivity(myintent);

            }
        });





        email = findViewById(R.id.et_email);
        user = findViewById(R.id.et_usernameup);
        pass = findViewById(R.id.et_passwordup);
        tel = findViewById(R.id.et_phone);
        signup = findViewById(R.id.button_insert);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "x", Toast.LENGTH_LONG).show();

                RequestQueue myQ = Volley.newRequestQueue(getApplicationContext());
                String URL = "http://192.168.1.105/android/index.php?new="+email.getText().toString()+"&pass=" + pass.getText().toString()+"&tel=" + tel.getText().toString();

                StringRequest myreq = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")){
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                        else{
                            Intent myintent = new Intent(getApplicationContext(),login.class);
                            startActivity(myintent);
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"err",Toast.LENGTH_LONG).show();

                    }
                });
                myQ.add(myreq);



            }
        });
    }
}