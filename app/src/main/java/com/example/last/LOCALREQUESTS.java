package com.example.last;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class LOCALREQUESTS {
    static String getuser(String email, String pass, Context appc)
    {
        String[] R = {"0"};
        RequestQueue myQ = Volley.newRequestQueue(appc);

        String URL = "http://192.168.1.105/android/index.php?email="+email+"&pass=" + pass;
        //String URL = "http://192.168.1.105/android/index.php?email=nono@gmail.com&pass=123";
        StringRequest myreq = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                R[0] = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(appc,"err",Toast.LENGTH_LONG).show();

            }
        });
        myQ.add(myreq);
        return R[0];
    }

}
