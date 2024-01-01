package com.example.primelaundryfyp.Customer;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.primelaundryfyp.Admin.usersListAdapter;
import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.Adapter.UserListAdapter;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.Model.customerModel;
import com.example.primelaundryfyp.R;
import com.google.firebase.firestore.DocumentSnapshot;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class customerList extends AppCompatActivity {


    private RecyclerView recyclerView;
    private FirebaseService firebaseService;
    private ArrayList<User> users;
    UserListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_list);

        firebaseService = new FirebaseService();
        users = new ArrayList<>();
        firebaseService.getCustomers(new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for(DocumentSnapshot m : model) {
                    User user = m.toObject(User.class);
                    users.add(user);
                }

                recyclerView = findViewById(R.id.customerRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(customerList.this));
                adapter = new UserListAdapter(customerList.this, users);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNotFound() {
                System.out.println("Not found");
            }

            @Override
            public void onFailRetrieval(Exception e) {
                System.out.println(e.getMessage());
            }
        });
    }
}

