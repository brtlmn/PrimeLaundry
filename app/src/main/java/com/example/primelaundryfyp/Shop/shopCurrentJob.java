package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.Customer.history;
import com.example.primelaundryfyp.Customer.statusCustomer;
import com.example.primelaundryfyp.Customer.viewStatusCustomer;
import com.example.primelaundryfyp.Driver.driverJobInfo;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Interface.SelectListener;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class shopCurrentJob extends AppCompatActivity implements SelectListener {


    private ImageView primeLaundryLogoHome10, historyLogo10, bookingLogo10, accountLogo11;
    private Button doneShop;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;
    private FirebaseService firebaseService;

    private RecyclerView recyclerView;
    CustomerHistoryAdapter adapter;
    ArrayList<Booking> items;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_current_job);


        items = new ArrayList<>();
        firebaseService = new FirebaseService();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();

        firebaseService.getCurrentBookingsByShop(user.getUid(), new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                for (DocumentSnapshot m : model){
                    Booking booking = m.toObject(Booking.class);
                    items.add(booking);
                }

                recyclerView = findViewById(R.id.cycleView);
                recyclerView.setLayoutManager(new LinearLayoutManager(shopCurrentJob.this));
                adapter = new CustomerHistoryAdapter(shopCurrentJob.this, items, shopCurrentJob.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });


        primeLaundryLogoHome10 = findViewById(R.id.primeLaundryLogoHome10);
        primeLaundryLogoHome10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, homepageShop.class);
                startActivity(intent);
            }
        });

        historyLogo10 = findViewById(R.id.historyLogo10);
        historyLogo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo10 = findViewById(R.id.bookingLogo10);
        bookingLogo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopCurrentJob.class);
                startActivity(intent);
            }
        });


        accountLogo11 = findViewById(R.id.accountLogo11);
        accountLogo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClicked(Booking booking) {
        Intent intent = new Intent(shopCurrentJob.this, shopJobInfo.class);
        intent.putExtra("bookingId", booking.getId());
        startActivity(intent);
    }
}
