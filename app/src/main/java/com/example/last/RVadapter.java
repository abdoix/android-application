package com.example.last;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RVadapter extends RecyclerView.Adapter<RVadapter.vholder>{
    ArrayList<ItemsModel> adapterlist = new ArrayList<ItemsModel>();
    private ItemClickListener clickListener;
    Context context;
    String ip;

    public RVadapter(Context context,ArrayList<ItemsModel> adapterlist,ItemClickListener clickListener) {
        //super(context,0,adapterlist);
        this.context = context;
        this.adapterlist = adapterlist;
        this.clickListener = clickListener;
        ip = context.getString(R.string.ip);
    }

    @NonNull
    @Override
    public RVadapter.vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowrv, parent, false);

        return new RVadapter.vholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVadapter.vholder holder, int position) {
        final ItemsModel mymodel = adapterlist.get(position);
        holder.name.setText(mymodel.getName());
        holder.desc.setText(mymodel.getDesc());
        holder.price.setText(mymodel.getPrixvoyage());
        Picasso.get().load("http://"+ip+"/android/image/"+mymodel.getImage()).into(holder.img);
        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onItemClick(adapterlist.get(holder.getAdapterPosition()));
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                Traitments.removeitem(mymodel.getId());
                adapterlist.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return adapterlist.size();
    }

    public void filter(ArrayList<ItemsModel> filteredList) {

        adapterlist = filteredList;
        notifyDataSetChanged();
    }

    public class vholder extends RecyclerView.ViewHolder {
        public TextView name,desc,price;
        public ImageView img,delete;
        public View root;
        public vholder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cardviewRVname);
            desc = (TextView) itemView.findViewById(R.id.cardviewRVdesc);
            price = (TextView) itemView.findViewById(R.id.pricecard);
            img = (ImageView) itemView.findViewById(R.id.cardviewRVimg);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            root = (View) itemView.findViewById(R.id.cardviewRV);



        }
    }


    public interface ItemClickListener {
        public void onItemClick(ItemsModel dataModel);
    }
}

