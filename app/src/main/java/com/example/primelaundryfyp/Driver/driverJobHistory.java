package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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

public class driverJobHistory extends AppCompatActivity implements SelectListener {
    private ImageView primeLaundryLogoHome6, historyLogo6, bookingLogo6, accountLogo4;

    private RecyclerView recyclerView;
    private FirebaseService firebaseService;
    CustomerHistoryAdapter adapter;
    ArrayList<Booking> items;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_job_history);

        items = new ArrayList<>();
        firebaseService = new FirebaseService();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseService.getHistoryBookingsByDriver(user.getUid(), new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for (DocumentSnapshot m : model){
                    Booking booking = m.toObject(Booking.class);
                    items.add(booking);
                }

                recyclerView = findViewById(R.id.cycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(driverJobHistory.this));
                adapter = new CustomerHistoryAdapter(driverJobHistory.this, items, driverJobHistory.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });


        primeLaundryLogoHome6 = findViewById(R.id.primeLaundryLogoHome6);
        primeLaundryLogoHome6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo6 = findViewById(R.id.historyLogo6);
        historyLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo6 = findViewById(R.id.bookingLogo6);
        bookingLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverJobInfo.class);
                startActivity(intent);
            }
        });


        accountLogo4 = findViewById(R.id.accountLogo4);
        accountLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverProfile.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onItemClicked(Booking booking) {

    }
}
