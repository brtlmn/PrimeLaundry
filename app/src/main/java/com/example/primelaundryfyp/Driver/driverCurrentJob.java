package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.Customer.statusCustomer;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Interface.SelectListener;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class driverCurrentJob extends AppCompatActivity implements SelectListener {

    private ImageView primeLaundryLogoHome8, historyLogo8, bookingLogo8, accountLogo9;

    private RecyclerView recyclerView;
    private FirebaseService firebaseService;
    CustomerHistoryAdapter adapter;
    ArrayList<Booking> items;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_current_job);

        items = new ArrayList<>();
        firebaseService = new FirebaseService();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();


        firebaseService.getCurrentBookingsByDriver(user.getUid(), new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for (DocumentSnapshot m : model){
                    Booking booking = m.toObject(Booking.class);
                    items.add(booking);
                }

                recyclerView = findViewById(R.id.cycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(driverCurrentJob.this));
                adapter = new CustomerHistoryAdapter(driverCurrentJob.this, items, driverCurrentJob.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });





        primeLaundryLogoHome8 = findViewById(R.id.primeLaundryLogoHome8);
        primeLaundryLogoHome8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo8 = findViewById(R.id.historyLogo8);
        historyLogo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo8 = findViewById(R.id.bookingLogo8);
        bookingLogo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverJobInfo.class);
                startActivity(intent);
            }
        });


        accountLogo9 = findViewById(R.id.accountLogo9);
        accountLogo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverProfile.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClicked(Booking booking) {

    }
}
