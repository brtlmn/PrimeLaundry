package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Customer.booking;
import com.example.primelaundryfyp.Customer.customerProfile;
import com.example.primelaundryfyp.Customer.history;
import com.example.primelaundryfyp.Customer.statusCustomer;

public class homepageCustomer extends AppCompatActivity {
    private ImageView bookingLogo,primeLaundryLogoHome, historyLogo,statusLogo, accountLogo;
    private CardView cardViewStartNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_customer);

        primeLaundryLogoHome = findViewById(R.id.primeLaundryLogoHome);
        primeLaundryLogoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo = findViewById(R.id.historyLogo);
        historyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo = findViewById(R.id.bookingLogo);
        bookingLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo = findViewById(R.id.statusLogo);
        statusLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        cardViewStartNow = findViewById(R.id.cardViewStartNow);
        cardViewStartNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, booking.class);
                startActivity(intent);
            }
        });

        accountLogo = findViewById(R.id.accountLogo);
        accountLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageCustomer.this, customerProfile.class);
                startActivity(intent);
            }
        });

    }
}


