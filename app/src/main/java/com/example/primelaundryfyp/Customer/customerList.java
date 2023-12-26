package com.example.primelaundryfyp.Customer;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Admin.usersListAdapter;
import com.example.primelaundryfyp.Model.customerModel;
import com.example.primelaundryfyp.R;

import java.util.ArrayList;

public class customerList extends AppCompatActivity {


    private RecyclerView recyclerView;
    private usersListAdapter usersListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Sample data for demonstration
        ArrayList<customerModel> customerList = generateSampleData();

        // Create and set the adapter
//        usersListAdapter = new usersListAdapter(customerList.this , customerList,  new OnItemClickListener() {
//            @Override
//            public void onItemClick(Customer customer) {
//                // Handle item click here
//                Toast.makeText(customerList.this, "Clicked on customer: " + customer.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });
        recyclerView.setAdapter(usersListAdapter);
    }

    private ArrayList<customerModel> generateSampleData() {
        ArrayList<customerModel> customerList = new ArrayList<>();
        // Add sample customers to the list
        customerList.add(new customerModel("UMP", "Abu", "012", "Customer" ));
        customerList.add(new customerModel("UMP", "Ali", "015", "Customer" ));
        customerList.add(new customerModel("UMP", "Siti", "013", "Customer" ));
        customerList.add(new customerModel("UMP", "Fatimah", "014", "Customer" ));
        customerList.add(new customerModel("UMP", "Humairah", "017", "Customer" ));
        // Add more customers as needed
        return customerList;
    }

}

