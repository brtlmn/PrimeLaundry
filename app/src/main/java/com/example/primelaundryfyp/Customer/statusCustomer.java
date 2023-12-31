package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;

public class statusCustomer extends AppCompatActivity {

    private ImageView bookingLogo3,primeLaundryLogoHome3, historyLogo3,statusLogo3, accountLogo7;
    private CardView cardViewListOfBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_status);

        cardViewListOfBooking = findViewById(R.id.cardViewListOfBooking);
        cardViewListOfBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, viewStatusCustomer.class);
                startActivity(intent);
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
}
