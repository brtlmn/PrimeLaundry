package com.example.primelaundryfyp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<User> data;

    public UserListAdapter(Context context, List<User> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.user_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = data.get(position);
        holder.nameView.setText(user.getName());
        holder.emailView.setText(user.getEmail());
        holder.phoneView.setText(user.getPhone_number());
        holder.addressView.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameView, emailView, phoneView, addressView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.nameView);
            emailView = itemView.findViewById(R.id.emailView);
            phoneView = itemView.findViewById(R.id.phoneView);
            addressView = itemView.findViewById(R.id.addressView);
        }
    }
}
