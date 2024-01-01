package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;

public class driverJobInfo extends AppCompatActivity {

    private ImageView primeLaundryLogoHome2, historyLogo2, bookingLogo2, accountLogo3;
    private Button acceptance;

    private String bookingId;
    private FirebaseService firebaseService;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private TextView laundryShop, pickupDate, deliveryDate, pickupTime, deliveryTime, total, status;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_job_info);

        firebaseService = new FirebaseService();
        Intent intent = getIntent();
        bookingId = intent.getStringExtra("bookingId");
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        laundryShop = findViewById(R.id.customerName);
        pickupDate = findViewById(R.id.pickupDate);
        deliveryDate = findViewById(R.id.deliveryDate);
        pickupTime = findViewById(R.id.pickupTime);
        deliveryTime = findViewById(R.id.deliveryTime);
        total = findViewById(R.id.total);
        status = findViewById(R.id.status);

        firebaseService.getBookingById(bookingId, new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                Booking booking = model.toObject(Booking.class);

                laundryShop.setText(booking.getShop_name());
                pickupDate.setText(booking.getPickup_date());
                deliveryDate.setText(booking.getDelivery_date());
                pickupTime.setText(booking.getPickup_time());
                deliveryTime.setText(booking.getDelivery_time());
                total.setText(booking.getTotal());
                status.setText(booking.getStatus());
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        primeLaundryLogoHome2 = findViewById(R.id.primeLaundryLogoHome2);
        primeLaundryLogoHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo2 = findViewById(R.id.historyLogo2);
        historyLogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo2 = findViewById(R.id.bookingLogo2);
        bookingLogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverJobInfo.class);
                startActivity(intent);
            }
        });

        accountLogo3 = findViewById(R.id.accountLogo3);
        accountLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverProfile.class);
                startActivity(intent);
            }
        });


        acceptance = findViewById(R.id.acceptance);
        acceptance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseService.updateBookingStatusDriver(bookingId, user.getUid(), new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Intent intent = new Intent(driverJobInfo.this, driverCurrentJob.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(driverJobInfo.this, "Failed to accept job", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
