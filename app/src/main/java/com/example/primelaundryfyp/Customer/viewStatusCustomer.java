package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;

public class viewStatusCustomer extends AppCompatActivity {

    private TextView paymentStatus,pickupStatus, deliveredStatus;
    private ImageView bookingLogo9,primeLaundryLogoHome9, historyLogo9,statusLogo9, accountLogo10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_view_status);


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
