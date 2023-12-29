package com.example.primelaundryfyp.Shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class shopList extends AppCompatActivity {

    RecyclerView recyclerView;
    DocumentReference documentReference;
    shopAdapter shopadapter;
    ArrayList <shopModel> list;
    private FirebaseDatabase firebasedatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference shopCollection = db.collection("Users");



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.shop_list));



        recyclerView = findViewById(R.id.shopRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirestoreRecyclerOptions<shopModel> options =
                new FirestoreRecyclerOptions.Builder<shopModel>()
                        .setQuery(shopCollection.whereEqualTo("User Type","Shop"), shopModel.class).build();
        shopadapter = new shopAdapter(options);
        recyclerView.setAdapter(shopadapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        shopadapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        shopadapter.stopListening();
    }
}
