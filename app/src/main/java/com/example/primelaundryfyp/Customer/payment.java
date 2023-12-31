package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;

public class payment extends AppCompatActivity {

    private TextView  typeCall, laundryShopCall, pickupDateCall, deliveryDateCall, pickupTimeCall, deliveryTimeCall, subtotalCall, pickupDeliveryFeeCall, taxCall, total;
    private ImageView primeLaundryLogoHome5, historyLogo5,bookingLogo5, statusLogo5, accountLogo6;
    private Button proceedPayment;
    private FirebaseAuth firebaseAuth;
    private String allType;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        firebaseService = new FirebaseService();
        typeCall = findViewById(R.id.typeCall);
        laundryShopCall = findViewById(R.id.laundryShopCall);
        pickupDateCall = findViewById(R.id.pickupDateCall);
        deliveryDateCall = findViewById(R.id.deliveryDateCall);
        pickupTimeCall = findViewById(R.id.pickupTimeCall);
        deliveryTimeCall = findViewById(R.id.deliveryTimeCall);
        subtotalCall = findViewById(R.id.subtotalCall);
        pickupDeliveryFeeCall = findViewById(R.id.pickupDeliveryFeeCall);
        taxCall = findViewById(R.id.taxCall);
        total = findViewById(R.id.total);
        proceedPayment = findViewById(R.id.proceedPayment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        Intent intent = getIntent();
        String bookingId = intent.getStringExtra("bookingId");

        firebaseService.getBookingById(bookingId, new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                Booking booking = model.toObject(Booking.class);

                ArrayList<String> types = new ArrayList<String>();
                types.add(booking.getIs_DryCleaning());
                types.add(booking.getIs_fold());
                types.add(booking.getIs_washDry());
                types.add(booking.getIs_iron());
                types.removeIf(String::isEmpty);
                allType = String.join(", ", types);

                typeCall.setText(allType);
                laundryShopCall.setText(booking.getShop_name());
                pickupDateCall.setText(booking.getPickup_date());
                deliveryDateCall.setText(booking.getDelivery_date());
                pickupTimeCall.setText(booking.getPickup_time());
                deliveryTimeCall.setText(booking.getDelivery_time());
                subtotalCall.setText(booking.getSub_total());
                pickupDeliveryFeeCall.setText(booking.getDelivery_fee());
                taxCall.setText(booking.getTax());
                total.setText(booking.getTotal());
            }

            @Override
            public void onNotFound() {
                Toast.makeText(payment.this, "Booking not found", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailRetrieval(Exception e) {
                Toast.makeText(payment.this, "Failed to get booking", Toast.LENGTH_SHORT).show();
            }
        });


        primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
        primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo5 = findViewById(R.id.historyLogo5);
        historyLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo5 = findViewById(R.id.statusLogo5);
        statusLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo6 = findViewById(R.id.accountLogo6);
        accountLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, customerProfile.class);
                startActivity(intent);
            }
        });

        proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, qrPayment.class);
                startActivity(intent);
                proceedPayment();
            }
        });
    }

    private void proceedPayment() {
        Toast.makeText(this, "PROCEED PAYMENT button clicked!", Toast.LENGTH_SHORT).show();
    }
}
