package com.example.primelaundryfyp.Driver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Model.driverModel;
import com.example.primelaundryfyp.Driver.driverAdapter;
import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Shop.shopAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class driverAdapter extends FirestoreRecyclerAdapter<driverModel, driverAdapter.MyViewHolder>  {

    public driverAdapter(@NonNull FirestoreRecyclerOptions<driverModel> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull driverAdapter.MyViewHolder holder, int position, @NonNull driverModel model) {
        holder.driverItemName.setText(model.getName());
        holder.driverItemPhoneNum.setText(model.getPhoneNumber());
        holder.driverItemAddress.setText(model.getAddress());
        holder.driverItemLicenseNum.setText(model.getLicenseNumber());
        holder.driverItemIcNum.setText(model.getICNum());
    }

    @NonNull
    @Override
    public driverAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_item,parent, false);
        return new driverAdapter.MyViewHolder(view);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView driverItemName,driverItemPhoneNum,driverItemAddress,driverItemLicenseNum, driverItemIcNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            driverItemName = itemView.findViewById(R.id.driverItemName);
            driverItemPhoneNum = itemView.findViewById(R.id.driverItemPhoneNum);
            driverItemAddress = itemView.findViewById(R.id.driverItemAddress);
            driverItemLicenseNum = itemView.findViewById(R.id.driverItemLicenseNum);
            driverItemIcNum = itemView.findViewById(R.id.driverItemIcNum);

        }
    }



}
