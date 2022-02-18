package com.example.mycloset;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Item> list;
    private FirebaseStorage storage;

    public MyAdapter(Context context, ArrayList<Item> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        storage = FirebaseStorage.getInstance();
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item  = list.get(position);
        Log.d("PTT", "onBindViewHolder: "+ item.toString());
        holder.type.setText(item.getType());
        holder.color.setText(item.getColor());
        holder.brand.setText(item.getBrand());
        holder.price.setText(item.getPrice());

        Glide
                .with(context)
                .load(item.getUrl())
                .centerCrop()
                .into(holder.pic);

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dataBaseRef = FirebaseDatabase.getInstance().getReference("Items").child(item.getUid());
                dataBaseRef.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView type,color,brand,price;
        public AppCompatImageView pic;
        public ImageButton removeButton;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.item_type);
            color = itemView.findViewById(R.id.item_color);
            brand = itemView.findViewById(R.id.item_brand);
            price = itemView.findViewById(R.id.item_price);
            pic = itemView.findViewById(R.id.item_picture);
            removeButton = itemView.findViewById(R.id.item_removeItem);


        }
    }
}
