package com.example.primelaundryfyp.Shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.UserListAdapter;
import com.example.primelaundryfyp.Customer.customerList;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class shopList extends AppCompatActivity {

    RecyclerView recyclerView;
    private FirebaseService firebaseService;
    private ArrayList<User> users;
    UserListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.shop_list));

        firebaseService = new FirebaseService();
        users = new ArrayList<>();
        firebaseService.getShops(new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for(DocumentSnapshot m : model) {
                    User user = m.toObject(User.class);
                    users.add(user);
                }

                recyclerView = findViewById(R.id.shopRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(shopList.this));
                adapter = new UserListAdapter(shopList.this, users);
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
