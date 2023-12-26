package com.example.primelaundryfyp.Admin;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.primelaundryfyp.Model.customerModel;
import com.example.primelaundryfyp.Model.driverModel;
import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class usersList extends AppCompatActivity implements usersListAdapter.UserItemClickListener {

    private RecyclerView recyclerView;
    private usersListAdapter adapter;
    private List<Object> userList; // List<Object> to hold different user types
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userList = new ArrayList<>();

        adapter = new usersListAdapter(this,userList,this);
        recyclerView.setAdapter(adapter);

    }

    private List<Object>getUsers() {
        List<Object> users = new ArrayList<>();
        users.add(new customerModel("address","name","phone","usertype"));
        users.add(new driverModel("name","address","phone","lesen","iC","usertype"));
        users.add(new shopModel("name","address","phone","ssm","ic","Usertype"));
        return users;
    }

    @Override
    public void onUserItemClick(Object user) {
        Toast.makeText(this, "kacing" + user.toString(), Toast.LENGTH_SHORT).show();
    }
}
