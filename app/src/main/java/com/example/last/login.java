package com.example.last;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {
    TextView signup,user,pass;
    Button Forgotpass,signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Forgotpass = findViewById(R.id.button_forgot_password);
        signup = findViewById(R.id.button_signup);


        Forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(),forgotpass.class);
                startActivity(myintent);
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(getApplicationContext(),signup.class);
                startActivity(myintent);
            }
        });




        //click login
        signin = findViewById(R.id.button_insert);
        user = findViewById(R.id.et_usernameup);
        pass = findViewById(R.id.et_passwordup);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue myQ = Volley.newRequestQueue(getApplicationContext());
                String URL = "http://192.168.1.103/android/index.php?email="+user.getText().toString()+"&pass=" + pass.getText().toString();
                //String URL = "http://192.168.1.105/android/index.php?email=nono@gmail.com&pass=123";
                StringRequest myreq = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("0")){
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                        }
                        else{
                            try {
                                JSONObject elm = new JSONObject(response);
                                Traitments.newuser(elm.getString("id"),elm.getString("email"),elm.getString("pass"),elm.getString("name"),elm.getString("tel"),elm.getString("date"));

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }




                            Intent myintent = new Intent(getApplicationContext(),MainActivity.class);
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
                //Toast.makeText(getApplicationContext(),user.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}