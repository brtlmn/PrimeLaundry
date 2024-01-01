package com.example.primelaundryfyp.Driver;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.UserListAdapter;
import com.example.primelaundryfyp.Customer.customerList;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.Model.driverModel;
import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Driver.driverAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class driverList extends AppCompatActivity {

    RecyclerView recyclerView;
    private FirebaseService firebaseService;
    private ArrayList<User> users;
    UserListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.driver_list));

        firebaseService = new FirebaseService();
        users = new ArrayList<>();
        firebaseService.getDrivers(new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for(DocumentSnapshot m : model) {
                    User user = m.toObject(User.class);
                    users.add(user);
                }

                recyclerView = findViewById(R.id.driverRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(driverList.this));
                adapter = new UserListAdapter(driverList.this, users);
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

