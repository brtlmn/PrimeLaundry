package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.Driver.driverCurrentJob;
import com.example.primelaundryfyp.Driver.driverJobHistory;
import com.example.primelaundryfyp.Driver.driverJobInfo;
import com.example.primelaundryfyp.Driver.driverProfile;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Interface.SelectListener;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class homepageDriver extends AppCompatActivity implements SelectListener {

    private ImageView primeLaundryLogoHome5, historyLogo5, bookingLogo5, accountLogo6;
    private FirebaseAuth firebaseAuth;
    private FirebaseService firebaseService;
    private RecyclerView recyclerView;
    CustomerHistoryAdapter adapter;
    ArrayList<Booking> items;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_driver);

        items = new ArrayList<>();
        firebaseService = new FirebaseService();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);

        firebaseService.getPendingBookings(new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for (DocumentSnapshot m : model){
                    Booking booking = m.toObject(Booking.class);
                    items.add(booking);
                }
                recyclerView = findViewById(R.id.cycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(homepageDriver.this));
                adapter = new CustomerHistoryAdapter(homepageDriver.this, items, homepageDriver.this);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
        primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo5 = findViewById(R.id.historyLogo5);
        historyLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverCurrentJob.class);
                startActivity(intent);
            }
        });


        accountLogo6 = findViewById(R.id.accountLogo6);
        accountLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(Booking booking) {
        Intent intent = new Intent(homepageDriver.this, driverJobInfo.class);
        intent.putExtra("bookingId", booking.getId());
        startActivity(intent);
    }
}
