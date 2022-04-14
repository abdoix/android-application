package com.example.last;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button loginbtn;
    TextView gomain;
    VideoView videoview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        if(Traitments.affuser() == null){
            loginbtn = findViewById(R.id.gologen);
            gomain = findViewById(R.id.gomain);
            videoview = (VideoView) findViewById(R.id.videoView);
            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.backv);
            videoview.setVideoURI(uri);
            videoview.start();

            loginbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),login.class));
                }
            });
            gomain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myintent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(myintent);
                }
            });
        }
        else{
            Intent myintent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(myintent);
        }


    }
}