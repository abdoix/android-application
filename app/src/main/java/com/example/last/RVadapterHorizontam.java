package com.example.last;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
    public class RVadapterHorizontam extends RecyclerView.Adapter<com.example.last.RVadapterHorizontam.vholder>{
        ArrayList<offersitems> adapterlist = new ArrayList<offersitems>();
        Context context;
        private ItemClickListener clickListener;
        String ip;

        public RVadapterHorizontam(Context context,ArrayList<offersitems> adapterlist,ItemClickListener clickListener) {
            this.context = context;
            this.adapterlist = adapterlist;
            this.clickListener = clickListener;
            ip = context.getString(R.string.ip);

        }

        @NonNull
        @Override
        public com.example.last.RVadapterHorizontam.vholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.colrv, parent, false);

            return new com.example.last.RVadapterHorizontam.vholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.last.RVadapterHorizontam.vholder holder, int position) {
            final offersitems mymodel = adapterlist.get(position);
            holder.name.setText(mymodel.getName());
            holder.oldprice.setText(String.valueOf(mymodel.getOldprice())+" $");
            holder.newprice.setText(String.valueOf(mymodel.getNewprice())+" $");
            Picasso.get().load("http://"+ip+"/android/image/"+mymodel.getImage()).into(holder.img);

            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClick(adapterlist.get(holder.getPosition()));

                }
            });


        }

        @Override
        public int getItemCount() {
            return adapterlist.size();
        }

        public class vholder extends RecyclerView.ViewHolder {
            public TextView name,oldprice,newprice;
            public ImageView img;
            public View root;
            public vholder(@NonNull View itemView) {
                super(itemView);
                root = (View) itemView.findViewById(R.id.cardview);
                name = (TextView) itemView.findViewById(R.id.cardviewtitle);
                oldprice = (TextView) itemView.findViewById(R.id.cardviewoldprice);
                newprice = (TextView) itemView.findViewById(R.id.cardviewnewprice);
                img = (ImageView) itemView.findViewById(R.id.cardviewimg);
                oldprice.setPaintFlags(oldprice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


            }
        }

        public interface ItemClickListener {
            public void onItemClick(offersitems dataModel);
        }
    }

