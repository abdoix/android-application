package com.example.last;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.function.ToLongBiFunction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link detailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class detailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public detailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment detailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static detailsFragment newInstance(String param1,String param2) {
        detailsFragment fragment = new detailsFragment();
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

    String ip;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_details, container, false);
        Toolbar back = v.findViewById(R.id.backtohome);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backto();
            }
        });

        TextView name = v.findViewById(R.id.detailstitle);
        TextView desc = v.findViewById(R.id.detailsdesc);
        TextView price = v.findViewById(R.id.detailsprice);
        TextView places = v.findViewById(R.id.detailsplaces);
        ImageView img = v.findViewById(R.id.detailsimg);
        ip = getString(R.string.ip);
        RequestQueue myQ = Volley.newRequestQueue(v.getContext());
        StringRequest myreq2 = new StringRequest(Request.Method.GET, "http://"+ip+"/android/index.php?id="+mParam1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //Toast.makeText(layout.getContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray myjson = new JSONArray(response);
                    for (int i=0;i<myjson.length();i++){
                        JSONObject element = myjson.getJSONObject(i);
                        String id = element.getString("id");
                        String names = element.getString("vd")+" -> "+element.getString("va");
                        String date = element.getString("date");
                        String nbrplace = element.getString("nbrp");
                        String prix = element.getString("prix");
                        Picasso.get().load("http://"+ip+"/android/image/"+element.getString("img")).into(img);
                        name.setText(names);
                        desc.setText("Morocco is a Northern African country, bordering the North Atlantic Ocean and the Mediterranean Sea, between Algeria and the annexed Western Sahara. It is one of only three nations (along with Spain and France) to have both Atlantic and Mediterranean coastlines. A large part of Morocco is mountainous.");
                        places.setText("places : "+nbrplace);
                        price.setText(prix +" $");


                        //itemsList.add(new popularitems(element.getString("vd")+" -> "+element.getString("va"),"Places : "+nbrplace+"     "+date,element.getString("img"),id,element.getString("hd"),element.getString("vd"),element.getString("ha"),element.getString("va"),element.getString("prix"),element.getString("date"),element.getString("nbrp"),oldp));

                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(v.getContext(),"err",Toast.LENGTH_LONG).show();

            }
        });
        myQ.add(myreq2);



        Button addto = v.findViewById(R.id.addto);
        ImageView fav = v.findViewById(R.id.detailsfav);

        addto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest myreq3 = new StringRequest(Request.Method.GET, "http://"+ip+"/android/index.php?id="+mParam1, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray myjson = new JSONArray(response);
                            for (int i=0;i<myjson.length();i++){
                                JSONObject element = myjson.getJSONObject(i);
                                String id = element.getString("id");
                                Traitments.additem(id,element.getString("hd") ,  element.getString("vd") ,  element.getString("ha"), element.getString("va"), element.getString("prix"), element.getString("date"),element.getString("nbrp"),element.getString("img"));

                            }

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(v.getContext(),"err",Toast.LENGTH_LONG).show();

                    }
                });
                myQ.add(myreq3);
            }
        });
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fav.setImageResource(R.drawable.ic_baseline_favorite_24);
            }
        });





        return v;
    }
    public void backto(){
        switch (mParam2){
            case "home":

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new home()).commit();
                //Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();
                break;
            case "card":

                //Toast.makeText(getApplicationContext(),"list",Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new Card()).commit();
                break;

        }
    }
}