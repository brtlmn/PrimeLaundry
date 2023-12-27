//package com.example.primelaundryfyp.Admin;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.primelaundryfyp.Model.customerModel;
//import com.example.primelaundryfyp.Model.driverModel;
//import com.example.primelaundryfyp.Model.shopModel;
//import com.example.primelaundryfyp.R;
//
//import java.util.List;
//
//public class usersListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private static final int TYPE_CUSTOMER = 1;
//    private static final int TYPE_DRIVER = 2;
//    private static final int TYPE_SHOP = 3;
//
//    private List<Object> userList;
//    private Context context;
//    private UserItemClickListener itemClickListener;
//
//    public interface UserItemClickListener {
//        void onUserItemClick(Object user);
//    }
//
//    public usersListAdapter(Context context, List<Object> userList, UserItemClickListener itemClickListener) {
//        this.context = context;
//        this.userList = userList;
//        this.itemClickListener = itemClickListener;
//    }
//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view;
//        switch (viewType) {
//            case TYPE_CUSTOMER:
//                view = LayoutInflater.from(context).inflate(R.layout.customer_list, parent, false);
//                return new CustomerViewHolder(view);
//            case TYPE_DRIVER:
//                view = LayoutInflater.from(context).inflate(R.layout.driver_list, parent, false);
//                return new DriverViewHolder(view);
//            case TYPE_SHOP:
//                view = LayoutInflater.from(context).inflate(R.layout.shop_list, parent, false);
//                return new ShopViewHolder(view);
//            default:
//                throw new IllegalArgumentException("Invalid view type");
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Object user = userList.get(position);
//
//        switch (holder.getItemViewType()) {
//            case TYPE_CUSTOMER:
//                ((CustomerViewHolder) holder).bind((customerModel) user);
//                break;
//            case TYPE_DRIVER:
//                ((DriverViewHolder) holder).bind((driverModel) user);
//                break;
//            case TYPE_SHOP:
//                ((ShopViewHolder) holder).bind((shopModel) user);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid view type");
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return userList.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        Object user = userList.get(position);
//        if (user instanceof customerModel) {
//            return TYPE_CUSTOMER;
//        } else if (user instanceof driverModel) {
//            return TYPE_DRIVER;
//        } else if (user instanceof shopModel) {
//            return TYPE_SHOP;
//        } else {
//            throw new IllegalArgumentException("Invalid object type");
//        }
//    }
//
//    // ViewHolder for Customer
//    private class CustomerViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView, emailTextView, phoneNumTextView, addressTextView;
//
//        CustomerViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.name);
//            emailTextView = itemView.findViewById(R.id.email);
//            phoneNumTextView = itemView.findViewById(R.id.phoneNum);
//            addressTextView = itemView.findViewById(R.id.address);
//
//            itemView.setOnClickListener(v -> {
//                if (itemClickListener != null) {
//                    itemClickListener.onUserItemClick(userList.get(getAdapterPosition()));
//                }
//            });
//        }
//
//        void bind(customerModel customer) {
//            nameTextView.setText(customer.getName());
////            emailTextView.setText(customer.getEmail());
//            phoneNumTextView.setText(customer.getPhoneNumber());
//            addressTextView.setText(customer.getAddress());
//        }
//    }
//
//    // ViewHolder for Driver
//    private class DriverViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView, emailTextView, phoneNumTextView, addressTextView, licenseNumberTextView, icNumTextView;
//
//        DriverViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.name);
//            emailTextView = itemView.findViewById(R.id.email);
//            phoneNumTextView = itemView.findViewById(R.id.phoneNum);
//            addressTextView = itemView.findViewById(R.id.address);
//            licenseNumberTextView = itemView.findViewById(R.id.licenseNumber);
//            icNumTextView = itemView.findViewById(R.id.icNum);
//
//            itemView.setOnClickListener(v -> {
//                if (itemClickListener != null) {
//                    itemClickListener.onUserItemClick(userList.get(getAdapterPosition()));
//                }
//            });
//        }
//
//        void bind(driverModel driver) {
//            nameTextView.setText(driver.getName());
////            emailTextView.setText(driver.getEmail());
//            phoneNumTextView.setText(driver.getPhoneNumber());
//            addressTextView.setText(driver.getAddress());
//            licenseNumberTextView.setText(driver.getLicenseNumber());
//            icNumTextView.setText(driver.getICNum());
//        }
//    }
//
//    // ViewHolder for Shop
//    private class ShopViewHolder extends RecyclerView.ViewHolder {
//        TextView nameTextView, emailTextView, phoneNumTextView, addressTextView, ssmNumberTextView, icNumTextView;
//
//        ShopViewHolder(@NonNull View itemView) {
//            super(itemView);
//            nameTextView = itemView.findViewById(R.id.name);
//            emailTextView = itemView.findViewById(R.id.email);
//            phoneNumTextView = itemView.findViewById(R.id.phoneNum);
//            addressTextView = itemView.findViewById(R.id.address);
//            ssmNumberTextView = itemView.findViewById(R.id.ssmNumber);
//            icNumTextView = itemView.findViewById(R.id.icNum);
//
//            itemView.setOnClickListener(v -> {
//                if (itemClickListener != null) {
//                    itemClickListener.onUserItemClick(userList.get(getAdapterPosition()));
//                }
//            });
//        }
//
//        void bind(shopModel shop) {
//            nameTextView.setText(shop.getName());
////            emailTextView.setText(shop.getEmail());
//            phoneNumTextView.setText(shop.getPhoneNumber());
//            addressTextView.setText(shop.getAddress());
//            ssmNumberTextView.setText(shop.getSSMNumber());
//            icNumTextView.setText(shop.getICNum());
//        }
//    }
//}
