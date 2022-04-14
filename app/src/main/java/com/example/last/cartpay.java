package com.example.last;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cartpay#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cartpay extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public cartpay() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment cartpay.
     */
    // TODO: Rename and change types and number of parameters
    public static cartpay newInstance(String param1, String param2) {
        cartpay fragment = new cartpay();
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
    TextView cardnumber,cardname,exp,cvv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cartpay, container, false);
        cardnumber = v.findViewById(R.id.cartnumber);
        cardname = v.findViewById(R.id.cartname);
        exp = v.findViewById(R.id.cartexp);
        cvv = v.findViewById(R.id.cartcvv);
        return v;
    }



    public int testcat(){
        String number = cardnumber.getText().toString();
        String name = cardname.getText().toString();
        String Sexp = exp.getText().toString();
        String Scvv = cvv.getText().toString();
        int result = 0;
        if(number.equals("123456789") && name.equals("TDM") && Sexp.equals("01/24") && Scvv.equals("123")) {
            result = 1;
        }
        return result;
    }
}