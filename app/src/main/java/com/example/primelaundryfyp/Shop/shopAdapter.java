package com.example.primelaundryfyp.Shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import java.util.ArrayList;

public class shopAdapter extends FirestoreRecyclerAdapter<shopModel,shopAdapter.MyViewHolder>{

    public shopAdapter(@NonNull FirestoreRecyclerOptions<shopModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull shopModel model) {
        holder.shopItemName.setText(model.getName());
        holder.shopItemPhoneNum.setText(model.getPhoneNumber());
        holder.shopItemAddress.setText(model.getAddress());
        holder.shopItemSsmNumber.setText(model.getSSMNumber());
        holder.shopItemIcNum.setText(model.getICNum());
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,parent, false);
        return new  MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView shopItemName,shopItemPhoneNum,shopItemAddress,shopItemSsmNumber, shopItemIcNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shopItemName = itemView.findViewById(R.id.shopItemName);
            shopItemPhoneNum = itemView.findViewById(R.id.shopItemPhoneNum);
            shopItemAddress = itemView.findViewById(R.id.shopItemAddress);
            shopItemSsmNumber = itemView.findViewById(R.id.shopItemSsmNumber);
            shopItemIcNum = itemView.findViewById(R.id.shopItemIcNum);


        }
    }
}
