package com.example.last;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.security.Permission;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class list extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment list.
     */
    // TODO: Rename and change types and number of parameters
    public static list newInstance(String param1, String param2) {
        list fragment = new list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView name,email;
    Button out;
    ImageView img;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_list, container, false);
        user onlineuser ;
        name = v.findViewById(R.id.usernameout);
        email = v.findViewById(R.id.emailout);
        if(Traitments.affuser() != null){
            onlineuser = Traitments.affuser();
            name.setText(onlineuser.getName());
            email.setText(onlineuser.getEmail());
        }




        //click to out
        out = v.findViewById(R.id.button_logout);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Traitments.logout();
                Intent myintent = new Intent(v.getContext(),HomeActivity.class);
                startActivity(myintent);
            }
        });


        //check runtime permission

        return v;
    }



}