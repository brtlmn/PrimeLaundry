package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.firestore.DocumentSnapshot;

public class viewStatusCustomer extends AppCompatActivity {

    private TextView paymentStatus,pickupStatus, deliveredStatus;
    private ImageView bookingLogo9,primeLaundryLogoHome9, historyLogo9,statusLogo9, accountLogo10;
    private String bookingId;
    private FirebaseService firebaseService;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_view_status);

        firebaseService = new FirebaseService();
        Intent intent = getIntent();
        bookingId = intent.getStringExtra("bookingId");

        paymentStatus = findViewById(R.id.paymentStatus);
        pickupStatus = findViewById(R.id.pickupStatus);
        deliveredStatus = findViewById(R.id.deliveredStatus);

        firebaseService.getBookingById(bookingId, new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                Booking booking = model.toObject(Booking.class);

                if (booking.getStatus().equals(new Constant().STATUS_PENDING)) {
                    pickupStatus.setVisibility(View.GONE);
                    deliveredStatus.setVisibility(View.GONE);
                }

                if (booking.getStatus().equals(new Constant().STATUS_PICKUP)) {
                    deliveredStatus.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        primeLaundryLogoHome9 = findViewById(R.id.primeLaundryLogoHome9);
        primeLaundryLogoHome9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewStatusCustomer.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo9 = findViewById(R.id.historyLogo9);
        historyLogo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewStatusCustomer.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo9 = findViewById(R.id.bookingLogo9);
        bookingLogo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewStatusCustomer.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo9 = findViewById(R.id.statusLogo9);
        statusLogo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewStatusCustomer.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo10 = findViewById(R.id.accountLogo10);
        accountLogo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(viewStatusCustomer.this, customerProfile.class);
                startActivity(intent);
            }
        });

    }
}
