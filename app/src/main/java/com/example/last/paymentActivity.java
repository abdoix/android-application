package com.example.last;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class paymentActivity extends AppCompatActivity {
    //android:id="@+id/open_dialog"
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Toolbar back = findViewById(R.id.backtocard);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getSupportFragmentManager().beginTransaction().replace(R.id.myframe, new Card()).commit();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });



        TextView master = (TextView) findViewById(R.id.fontmastercard);
        Typeface fontmaster = Typeface.createFromAsset (getAssets(), "fa-brands-400.ttf");
        master.setTypeface(fontmaster);
        master.setText("\uf1f1");

        TextView visa = (TextView) findViewById(R.id.fontvisa);
        Typeface fontvisa = Typeface.createFromAsset (getAssets(), "fa-brands-400.ttf");
        visa.setTypeface(fontvisa);
        visa.setText("\uf1f0");

        TextView pay = (TextView) findViewById(R.id.fontpay);
        Typeface fontpay = Typeface.createFromAsset (getAssets(), "fa-brands-400.ttf");
        pay.setTypeface(fontpay);
        pay.setText("\uf1ed");

        Button click;
        click = findViewById(R.id.procces);
        int cmp=0;
        TextView[] texts = {master,visa,pay};
        for (TextView item : texts) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView itemclick = (TextView) view;

                    if(itemclick.getCurrentTextColor() == getResources().getColor(R.color.colorchange)){
                        for (TextView item2 : texts) {
                            item2.setTextColor(getResources().getColor(R.color.colorhover));
                        }
                    }
                    else{
                        if(itemclick == pay)
                        {
                            paypal fragmentcart = new paypal();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentpay, fragmentcart).commit();
                            click.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //int result = active == "paypal"? fragmentpay.testcat(): fragmentcart.testcat();
                                    int result = fragmentcart.testpay();
                                    if(result == 1) {
                                        showCustomDialog();
                                    }
                                    else{
                                        showCustomDialogerror();
                                    }

                                }
                            });
                        }
                        else{
                            cartpay fragmentcart = new cartpay();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentpay, fragmentcart).commit();

                            click.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    //int result = active == "paypal"? fragmentpay.testcat(): fragmentcart.testcat();
                                    int result = fragmentcart.testcat();
                                    if(result == 1) {
                                        showCustomDialog();
                                    }
                                    else{
                                        showCustomDialogerror();
                                    }

                                }
                            });
                        }
                        for (TextView item2 : texts) {

                            item2.setTextColor(getResources().getColor(R.color.colorhover));

                        }
                        itemclick.setTextColor(getResources().getColor(R.color.colorchange));

                    }

                }
            });
        }

        //payment test

    }

  //Function to display the custom dialog.
    void showCustomDialog() {
        final Dialog dialog = new Dialog(paymentActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.custom_dialog);
        //Initializing the views of the dialog.
        final CheckBox termsCb = dialog.findViewById(R.id.terms_cb);
        Button submitButton = dialog.findViewById(R.id.submit_button);
        Button exitButton = dialog.findViewById(R.id.submit_buttonexit);
        TextView totalprx = dialog.findViewById(R.id.dialogtotal);
        ListView tickets = dialog.findViewById(R.id.ticket);
        totalprx.setText(String.valueOf(Traitments.totalprix())+"$");

        ArrayList<String> stickets =new ArrayList<>();
        //{"item1                                              16$"};
        Cursor cur = Traitments.getitems();
        if(cur.moveToFirst()){
            do {
                String  name = cur.getString(2)+" -> "+cur.getString(4);
                String  desc = "date : "+cur.getString(6);
                String  prix = cur.getString(5);
                stickets.add(name+"                                              "+prix+"$");

            }while(cur.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,stickets);

        tickets.setAdapter(adapter);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean hasAccepted = termsCb.isChecked();
                //populateInfoTv(name,age,hasAccepted);
                dialog.dismiss();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean hasAccepted = termsCb.isChecked();
                //populateInfoTv(name,age,hasAccepted);
                dialog.dismiss();
                finishAffinity();
            }
        });

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
    }
    void showCustomDialogerror() {
        final Dialog dialog = new Dialog(paymentActivity.this);
        //We have added a title in the custom layout. So let's disable the default title.
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //The user will be able to cancel the dialog bu clicking anywhere outside the dialog.
        dialog.setCancelable(true);
        //Mention the name of the layout of your custom dialog.
        dialog.setContentView(R.layout.custom_dialog_error);
        //Initializing the views of the dialog.

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT);
    }

    void populateInfoTv(String name, String age, Boolean hasAcceptedTerms) {
        //infoTv.setVisibility(View.VISIBLE);
        //String acceptedText = "have";
        //if(!hasAcceptedTerms) {
            //acceptedText = "have not";
        //}
        //infoTv.setText(String.format(getString(R.string.info), name, age, acceptedText));
    }
}