package com.example.last;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {



    private ArrayList<popularitems> itemsModelList;
    private ArrayList<popularitems> itemsModellistFiltered;
    private LayoutInflater mInflater;
    private ItemClickListener clickListener;
    String ip;

    public CustomAdapter(ArrayList<popularitems> itemsModelList, Context context,ItemClickListener clickListener) {
        this.itemsModelList = new ArrayList<popularitems>();
        this.itemsModellistFiltered = new ArrayList<popularitems>();
        this.itemsModelList = itemsModelList;
        this.itemsModellistFiltered = itemsModelList;
        mInflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        ip = context.getString(R.string.ip);
    }


    @Override
    public int getCount() {
        return itemsModellistFiltered.size();
    }

    @Override
    public Object getItem(int i) {
        return itemsModellistFiltered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View newview = mInflater.inflate(R.layout.rowitem, null);
        popularitems item = itemsModellistFiltered.get(position);
        CardView root = newview.findViewById(R.id.cardviewroot);
        ImageView image = (ImageView) newview.findViewById(R.id.tvimg);
        TextView tvNames = (TextView) newview.findViewById(R.id.tvName);
        TextView tvDesc = (TextView) newview.findViewById(R.id.tvDesc);
        TextView tvprice = (TextView) newview.findViewById(R.id.popularprice);

        Picasso.get().load("http://"+ip+"/android/image/"+item.getImage()).into(image);
        tvNames.setText(item.getName());
        tvDesc.setText(String.valueOf(item.getDesc()));
        tvprice.setText(String.valueOf(item.getPrice())+" $");


        ImageView cardadd = (ImageView) newview.findViewById(R.id.cardview1add);
        cardadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(newview.getContext(),itemsModellistFiltered.get(position).getId(), Toast.LENGTH_LONG).show();


                popularitems item = itemsModellistFiltered.get(position);

                Traitments.additem(item.getId(),item.getHeuredepart() ,  item.getVilledepart() ,  item.getHeurearrivee(), item.getVillearrivee(), item.getPrixvoyage(), item.getDated(),item.getNbrplaces(),item.getImage());
            }
        });


        //root click



        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClickpop(itemsModellistFiltered.get(position));

            }
        });



        return newview;
    }

    public void newupdate(ArrayList<popularitems> itemsListfilter) {
        itemsModellistFiltered = itemsListfilter;
        //notifyDataSetChanged();
    }

    public interface ItemClickListener {
        public void onItemClickpop(popularitems dataModel);
    }
}
