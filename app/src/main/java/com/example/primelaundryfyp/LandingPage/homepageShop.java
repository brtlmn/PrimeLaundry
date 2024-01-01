package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Driver.driverCurrentJob;
import com.example.primelaundryfyp.Driver.driverJobHistory;
import com.example.primelaundryfyp.Driver.driverProfile;
import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Shop.shopCurrentJob;
import com.example.primelaundryfyp.Shop.shopHistory;
import com.example.primelaundryfyp.Shop.shopProfile;

public class homepageShop extends AppCompatActivity {

    private ImageView primeLaundryLogoHome7, historyLogo7, bookingLogo7, accountLogo8;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_shop);


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

