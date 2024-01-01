package com.example.primelaundryfyp.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Interface.SelectListener;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;

import java.util.List;

public class CustomerHistoryAdapter extends RecyclerView.Adapter<CustomerHistoryAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Booking> data;
    private SelectListener selectListener;
    public CustomerHistoryAdapter(Context context, List<Booking> data, SelectListener selectListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.selectListener = selectListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Booking booking = data.get(position);
        holder.laundryShop.setText(booking.getShop_name());
        holder.pickupDate.setText(booking.getPickup_date());
        holder.deliveryDate.setText(booking.getDelivery_date());
        holder.pickupTime.setText(booking.getPickup_time());
        holder.deliveryTime.setText(booking.getDelivery_time());
        holder.total.setText(booking.getTotal());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectListener.onItemClicked(data.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder {

        TextView laundryShop, pickupDate,deliveryDate,pickupTime, deliveryTime,total;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            laundryShop = itemView.findViewById(R.id.customerName);
            pickupDate = itemView.findViewById(R.id.pickupDate);
            deliveryDate = itemView.findViewById(R.id.deliveryDate);
            pickupTime = itemView.findViewById(R.id.pickupTime);
            deliveryTime = itemView.findViewById(R.id.deliveryTime);
            total = itemView.findViewById(R.id.total);
            cardView = itemView.findViewById(R.id.historyCard);
        }
    }
}
