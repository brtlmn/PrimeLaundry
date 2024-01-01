package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Interface.SelectListener;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class statusCustomer extends AppCompatActivity implements SelectListener {

    private ImageView bookingLogo3,primeLaundryLogoHome3, historyLogo3,statusLogo3, accountLogo7;

    private RecyclerView recyclerView;
    private FirebaseService firebaseService;
    CustomerHistoryAdapter adapter;
    ArrayList<Booking> items;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_status);

        items = new ArrayList<>();
        firebaseService = new FirebaseService();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseService.getCurrentBookingsByCustomer(user.getUid(), new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for (DocumentSnapshot m : model){
                    Booking booking = m.toObject(Booking.class);
                    items.add(booking);
                }

                recyclerView = findViewById(R.id.cycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(statusCustomer.this));
                adapter = new CustomerHistoryAdapter(statusCustomer.this, items, statusCustomer.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        primeLaundryLogoHome3 = findViewById(R.id.primeLaundryLogoHome3);
        primeLaundryLogoHome3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo3 = findViewById(R.id.historyLogo3);
        historyLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo3 = findViewById(R.id.bookingLogo3);
        bookingLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo3 = findViewById(R.id.statusLogo3);
        statusLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo7 = findViewById(R.id.accountLogo7);
        accountLogo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, customerProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(Booking booking) {
        Intent intent = new Intent(statusCustomer.this, viewStatusCustomer.class);
        intent.putExtra("bookingId", booking.getId());
        startActivity(intent);
    }
}
