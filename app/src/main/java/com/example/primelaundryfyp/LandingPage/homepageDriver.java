package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Customer.booking;
import com.example.primelaundryfyp.Driver.driverProfile;
import com.example.primelaundryfyp.Driver.pickupDelivery;
import com.example.primelaundryfyp.R;

public class homepageDriver extends AppCompatActivity {

    private ImageView primeLaundryLogoHome5, historyLogo5, bookingLogo5, statusLogo5, accountLogo6;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_driver);

        primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
        primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo5 = findViewById(R.id.historyLogo5);
        historyLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, pickupDelivery.class);
                startActivity(intent);
            }
        });


//        accountLogo6 = findViewById(R.id.accountLogo6);
//        accountLogo6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(homepageDriver.this, driverProfile.class);
//                startActivity(intent);
//            }
//        });  TAKDE TAU STATUS NK BUAT MACAM MANA

        accountLogo6 = findViewById(R.id.accountLogo6);
        accountLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverProfile.class);
                startActivity(intent);
            }
        });
    }
}
