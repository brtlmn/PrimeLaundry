package com.example.primelaundryfyp.Shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class shopList extends AppCompatActivity {

    RecyclerView recyclerView;
    DocumentReference documentReference;
    shopAdapter shopadapter;
    ArrayList <shopModel> list;
    private FirebaseDatabase firebasedatabase;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.shop_list));



        recyclerView = findViewById(R.id.shopRecyclerView);
        FirebaseApp.initializeApp(this);
        firebasedatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference  = firebasedatabase.getReference().child("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<shopModel>();
        shopadapter = new shopAdapter(this,list);
        recyclerView.setAdapter(shopadapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    shopModel shopmodel = dataSnapshot.getValue(shopModel.class);
                    System.out.println(shopmodel.getName());
                    list.add(shopmodel);
                }
                shopadapter= new shopAdapter(shopList.this, list);
                recyclerView.setAdapter(shopadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
