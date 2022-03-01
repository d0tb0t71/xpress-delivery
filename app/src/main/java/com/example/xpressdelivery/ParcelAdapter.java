package com.example.xpressdelivery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ParcelAdapter extends RecyclerView.Adapter<ParcelAdapter.MyViewHolder> {
    Context context;
    ArrayList<ParcelModel> list;

    public ParcelAdapter(Context context, ArrayList<ParcelModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.parcel_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ParcelModel parcelModel = list.get(position);

        holder.parcelID_tv.setText(parcelModel.getpID());
        holder.sender_tv.setText("Sender : "+parcelModel.getsName());
        holder.receiver_tv.setText("Receiver : "+parcelModel.getrName());
        holder.issue_date_tv.setText("Issue Date : "+parcelModel.getIssueDate());
        holder.product_type_tv.setText("Product Type : "+parcelModel.getpType());


        holder.show_details_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,ParcelDetails.class);
                intent.putExtra("pID", parcelModel.getpID());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filteredList(ArrayList<ParcelModel> filterList) {
        list = filterList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView parcelID_tv,sender_tv,receiver_tv,issue_date_tv,product_type_tv;
        Button show_details_btn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            parcelID_tv = itemView.findViewById(R.id.parcelID_tv);
            sender_tv = itemView.findViewById(R.id.sender_tv);
            receiver_tv = itemView.findViewById(R.id.receiver_tv);
            issue_date_tv = itemView.findViewById(R.id.issue_date_tv);
            product_type_tv = itemView.findViewById(R.id.product_type_tv);
            show_details_btn = itemView.findViewById(R.id.show_details_btn);

        }
    }
}