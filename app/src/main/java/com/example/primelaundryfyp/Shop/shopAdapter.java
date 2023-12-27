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
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class shopAdapter extends RecyclerView.Adapter<shopAdapter.MyViewHolder>{


    Context context;
    ArrayList<shopModel> list;

    public shopAdapter(Context context, ArrayList<shopModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_item,parent, false );
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        shopModel shopmodel = list.get(position);
        holder.shopItemName.setText(shopmodel.getName());
        holder.shopItemPhoneNum.setText(shopmodel.getPhoneNumber());
        holder.shopItemAddress.setText(shopmodel.getAddress());
        holder.shopItemSsmNumber.setText(shopmodel.getSSMNumber());
        holder.shopItemIcNum.setText(shopmodel.getICNum());
    }

    @Override
    public int getItemCount() {
        return list.size();
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
