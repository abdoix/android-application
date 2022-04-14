package com.example.last;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;


import android.provider.Settings;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home extends Fragment implements RVadapterHorizontam.ItemClickListener,CustomAdapter.ItemClickListener {


    public home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment home.
     */
    // TODO: Rename and change types and number of parameters
    public static home newInstance() {
        home fragment = new home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    Context c;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        c = context;
    }


    GridView gridview;
    AutoCompleteTextView filterS;
    CustomAdapter customAdapter;
    ArrayList<popularitems> itemsList;
    TextView user ,all,today,month,nat,inter;
    String ip;
    RequestQueue myQ;
    String URL;
    ArrayList<offersitems> offerlist;
    RVadapterHorizontam horizontamada = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment








        View layout = inflater.inflate(R.layout.fragment_home, container, false);


        ip = getString(R.string.ip);
        myQ = Volley.newRequestQueue(layout.getContext());
        URL = "http://"+ip+"/android/index.php";
        //list filter
        TextView[] list = {layout.findViewById(R.id.homeall),layout.findViewById(R.id.hometoday),layout.findViewById(R.id.homemonth),layout.findViewById(R.id.homenational),layout.findViewById(R.id.homeinter)};

        for (TextView item : list) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (TextView item2 : list) {
                        item2.setBackgroundResource(0);
                        item2.setTextColor(getResources().getColor(R.color.md_grey_700));
                    }
                    TextView currentclikc = (TextView) view;
                    String text = currentclikc.getText().toString();
                    currentclikc.setBackground(getContext().getDrawable(R.drawable.listitem));

                    currentclikc.setTextColor(getResources().getColor(R.color.semi_black));

                    listfilter(text);
                    //Toast.makeText(getContext(), currentclikc.getText().toString(),Toast.LENGTH_LONG).show();
                }
            });
        }







        //list filter





        user = layout.findViewById(R.id.user);
        if(Traitments.affuser() != null){
            user onlineuser = Traitments.affuser();
            user.setText("Hello, "+onlineuser.getName()+"!");
        }



        filterS = (AutoCompleteTextView) layout.findViewById(R.id.searchfilterGrid);

        String[] COUNTRIES = {"tanger", "casa", "marrakech", "asilah", "dubai", "amsterdam", "knitra", "new york"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, COUNTRIES);
        filterS.setAdapter(adapter);
        //Horizontal rcycler
        offerlist = new ArrayList<offersitems>();
        RecyclerView mycard = layout.findViewById(R.id.rcyhori);



        //get data use volley
        if(horizontamada == null)
        {

            StringRequest myreq = new StringRequest(Request.Method.GET, URL+"?sold=nothing", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    //Toast.makeText(layout.getContext(), response, Toast.LENGTH_LONG).show();
                    try {
                        JSONArray myjson = new JSONArray(response);
                        for (int i=0;i<myjson.length();i++){
                            JSONObject element = myjson.getJSONObject(i);
                            String id = element.getString("id");
                            float oldp = Float.parseFloat(element.getString("prix"));
                            float newp = Float.parseFloat("0."+element.getString("sold").toString());
                            newp = oldp*newp;
                            String desc = "Places : "+element.getString("nbrp")+"     "+element.getString("date");
                            offerlist.add(new offersitems(element.getString("vd")+" -> "+element.getString("va"),desc,element.getString("img"),id,element.getString("hd"),element.getString("vd"),element.getString("ha"),element.getString("va"),element.getString("prix"),element.getString("date"),element.getString("nbrp"),oldp,newp));

                        }

                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(layout.getContext(),"err",Toast.LENGTH_LONG).show();

                }
            });
            myQ.add(myreq);

        }










        LinearLayoutManager mylinear= new LinearLayoutManager(c, LinearLayoutManager.HORIZONTAL, false);
        horizontamada = new RVadapterHorizontam(c,offerlist,this);
        mycard.setAdapter(horizontamada);
        mycard.setLayoutManager(mylinear);














        //Gridview

        itemsList = new ArrayList<popularitems>();
        gridview = (GridView) layout.findViewById(R.id.homegrid);
        StringRequest myreq2 = new StringRequest(Request.Method.GET, URL+"?popular=nothing", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //Toast.makeText(layout.getContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray myjson = new JSONArray(response);
                    ViewGroup.LayoutParams layoutParams = gridview.getLayoutParams();
                    layoutParams.height = (int)((230 *  Math.ceil(myjson.length()%2 == 0 ? myjson.length()/2 :(myjson.length()/2)+1)) * getResources().getDisplayMetrics().density); //this is in pixels
                    gridview.setLayoutParams(layoutParams);
                    for (int i=0;i<myjson.length();i++){
                        JSONObject element = myjson.getJSONObject(i);
                        String id = element.getString("id");
                        String date = element.getString("date");
                        String nbrplace = element.getString("nbrp");
                        String oldp = element.getString("prix");

                        itemsList.add(new popularitems(element.getString("vd")+" -> "+element.getString("va"),"Places : "+nbrplace+"     "+date,element.getString("img"),id,element.getString("hd"),element.getString("vd"),element.getString("ha"),element.getString("va"),element.getString("prix"),element.getString("date"),element.getString("nbrp"),oldp));
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(layout.getContext(),"err",Toast.LENGTH_LONG).show();

            }
        });
        myQ.add(myreq2);







        customAdapter = new CustomAdapter(itemsList,c, (CustomAdapter.ItemClickListener) this);

        gridview.setAdapter(customAdapter);

        //Toast.makeText(getContext(),String.valueOf(customAdapter.getCount()),Toast.LENGTH_LONG).show();


        filterS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
        return layout;
    }


    @Override
    public void onItemClick(offersitems dataModel) {
        Fragment fragment = detailsFragment.newInstance(dataModel.getId(),"home");

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe,fragment).commit();

    }

    @Override
    public void onItemClickpop(popularitems dataModel) {
        Fragment fragment = detailsFragment.newInstance(dataModel.getId(),"home");

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe,fragment).commit();
    }






    public void listfilter(String itemfilter){
        ArrayList<popularitems> itemsListfilter = new ArrayList<popularitems>();
        StringRequest myrqfilter = new StringRequest(Request.Method.GET, URL+"?filter="+itemfilter, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                //Toast.makeText(layout.getContext(), response, Toast.LENGTH_LONG).show();
                try {
                    JSONArray myjson = new JSONArray(response);
                    ViewGroup.LayoutParams layoutParams = gridview.getLayoutParams();
                    layoutParams.height = (int)((230 *  Math.ceil(myjson.length()%2 == 0 ? myjson.length()/2 :(myjson.length()/2)+1)) * getResources().getDisplayMetrics().density); //this is in pixels
                    gridview.setLayoutParams(layoutParams);
                    Toast.makeText(getContext(), String.valueOf(myjson.length()/2), Toast.LENGTH_LONG).show();

                    for (int i=0;i<myjson.length();i++){
                        JSONObject element = myjson.getJSONObject(i);
                        String id = element.getString("id");
                        String date = element.getString("date");
                        String nbrplace = element.getString("nbrp");
                        String oldp = element.getString("prix");


                        itemsListfilter.add(new popularitems(element.getString("vd")+" -> "+element.getString("va"),"Places : "+nbrplace+"     "+date,element.getString("img"),id,element.getString("hd"),element.getString("vd"),element.getString("ha"),element.getString("va"),element.getString("prix"),element.getString("date"),element.getString("nbrp"),oldp));
                        //Toast.makeText(getContext(), itemsListfilter.get(0).getId(), Toast.LENGTH_LONG).show();
                    }

                }
                catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"err",Toast.LENGTH_LONG).show();

            }
        });
        myQ.add(myrqfilter);







        customAdapter.newupdate(itemsListfilter);

    }














    public void filter(String newtext){
        ArrayList<popularitems> filteredList = new ArrayList<popularitems>();
        for (popularitems item : itemsList){
            if (item.getName().toLowerCase().contains (newtext.toLowerCase())){
                filteredList.add(item);
            }
        }
        customAdapter.newupdate(filteredList);
    }
}







