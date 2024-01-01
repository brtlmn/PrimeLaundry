package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Shop.shopCurrentJob;
import com.example.primelaundryfyp.Shop.shopHistory;
import com.example.primelaundryfyp.Shop.shopProfile;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class homepageShop extends AppCompatActivity {

    private ImageView primeLaundryLogoHome7, historyLogo7, bookingLogo7, accountLogo8;
    private TextView totalBooking, totalSale;

    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private String totalSaleString, totalBookingString;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_shop);

        totalBooking = findViewById(R.id.pickupDate);
        totalSale = findViewById(R.id.customerName);

        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        CollectionReference collectionReference = firestore.collection("Bookings");
        collectionReference.whereEqualTo("shop_id", user.getUid()).get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    double totalDouble = 0.0;
                    Integer totalJob = 0;
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Double amount = Double.valueOf(document.getString("total"));
                        totalJob++;
                        if (amount != null) {
                            totalDouble += amount;
                        }
                    }

                    totalSaleString = String.valueOf(totalDouble);
                    totalSale.setText(totalSaleString);
                    totalBooking.setText(String.valueOf(totalJob));

                })
                .addOnFailureListener(e -> {
                });


        primeLaundryLogoHome7 = findViewById(R.id.primeLaundryLogoHome7);
        primeLaundryLogoHome7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageShop.this, homepageShop.class);
                startActivity(intent);
            }
        });

        historyLogo7 = findViewById(R.id.historyLogo7);
        historyLogo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageShop.this, shopHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo7 = findViewById(R.id.bookingLogo7);
        bookingLogo7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageShop.this, shopCurrentJob.class);
                startActivity(intent);
            }
        });


        accountLogo8 = findViewById(R.id.accountLogo8);
        accountLogo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageShop.this, shopProfile.class);
                startActivity(intent);
            }
        });
    }
}

