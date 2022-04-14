package com.example.last;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Card#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Card extends Fragment implements RVadapter.ItemClickListener {

    public Card() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Card.
     */
    // TODO: Rename and change types and number of parameters
    public static Card newInstance() {
        Card fragment = new Card();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }
    Context c =null;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        c = context;
    }

    @Override
    public void onItemClick(ItemsModel dataModel) {
        Fragment fragment = detailsFragment.newInstance(dataModel.getId(),"card");

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe,fragment).commit();
    }



    public interface FragmentAListener {
        void onInputASent(CharSequence input);
    }

    ArrayList<ItemsModel> mylist;
    AutoCompleteTextView mysearch;
    RVadapter myadapter;
    Button  checkout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_card, container, false);
        //set total

        TextView total = v.findViewById(R.id.totalprice);
        total.setText("Total : "+String.valueOf(Traitments.totalprix())+"$");

         mylist = new ArrayList<ItemsModel>();



        RecyclerView mycard = v.findViewById(R.id.cardrv);



        Cursor cur = Traitments.getitems();
        if(cur.moveToFirst()){
            do {
                String  name= cur.getString(2)+" -> "+cur.getString(4);
                String  desc= "date : "+cur.getString(6);
                mylist.add(new ItemsModel(name,desc,cur.getString(8),cur.getString(0),cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4),cur.getString(5)+" $",cur.getString(6),cur.getString(7)));

            }while(cur.moveToNext());
        }



        LinearLayoutManager mylinear= new LinearLayoutManager(c, LinearLayoutManager.VERTICAL, false);
        myadapter = new RVadapter(c,mylist,this);
        mycard.setAdapter(myadapter);
        mycard.setItemAnimator(new DefaultItemAnimator());
        mycard.setLayoutManager(mylinear);

        //search
        mysearch = v.findViewById(R.id.searchviewcard);

        String[] COUNTRIES = {"tanger", "casa", "marrakech", "asilah", "dubai", "amsterdam", "knitra", "new york"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( getContext(),android.R.layout.simple_list_item_1, COUNTRIES);
        mysearch.setAdapter(adapter);

        mysearch.addTextChangedListener(new TextWatcher() {
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


        //search





        // checkoutt
            checkout = v.findViewById(R.id.chechout);

            checkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Fragment fragment = detailsFragment.newInstance(dataModel.getId(),"card");
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.myframe,new payment()).commit();
                    startActivity(new Intent(getActivity().getApplicationContext(),paymentActivity.class));
                }
            });
        // checkoutt
        return v;
    }
    public void filter(String newtext){
        ArrayList<ItemsModel> filteredList = new ArrayList<ItemsModel>();
        for (ItemsModel item : mylist){
            if (item.getName().toLowerCase().contains (newtext.toLowerCase())){
                filteredList.add(item);
            }
        }
        myadapter.filter(filteredList);
    }
}