package com.example.last;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new home()).commit();

        BottomNavigationView BN = (BottomNavigationView) findViewById(R.id.N);
        BN.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.Home:
                        fragment = home.newInstance();
                        getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new home()).commit();
                        //Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.acount:
                        //Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new list()).commit();
                        break;
                    case R.id.Card:
                        fragment = Card.newInstance();
                        //Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new Card()).commit();
                        break;
                    case R.id.Maps:
                        //Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new Maps()).commit();
                        break;
                }
                return true;
            }
        });




    }
}