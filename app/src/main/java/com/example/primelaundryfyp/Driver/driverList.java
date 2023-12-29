package com.example.primelaundryfyp.Driver;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Model.driverModel;
import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Driver.driverAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class driverList extends AppCompatActivity {

    RecyclerView recyclerView;
    DocumentReference documentReference;
    driverAdapter driveradapter;
    ArrayList<driverModel> list;
    private FirebaseDatabase firebasedatabase;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference driverCollection = db.collection("Users");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((R.layout.driver_list));



        recyclerView = findViewById(R.id.driverRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirestoreRecyclerOptions<driverModel> options =
                new FirestoreRecyclerOptions.Builder<driverModel>()
                        .setQuery(driverCollection.whereEqualTo("User Type","Driver"), driverModel.class).build();
        driveradapter = new driverAdapter(options);
        recyclerView.setAdapter(driveradapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        driveradapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        driveradapter.stopListening();
    }
}

