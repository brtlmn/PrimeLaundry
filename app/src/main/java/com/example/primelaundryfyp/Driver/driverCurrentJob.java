package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.R;

public class driverCurrentJob extends AppCompatActivity {

    private ImageView primeLaundryLogoHome8, historyLogo8, bookingLogo8, accountLogo9;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_current_job);

        primeLaundryLogoHome8 = findViewById(R.id.primeLaundryLogoHome8);
        primeLaundryLogoHome8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo8 = findViewById(R.id.historyLogo8);
        historyLogo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo8 = findViewById(R.id.bookingLogo8);
        bookingLogo8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverJobInfo.class);
                startActivity(intent);
            }
        });


        accountLogo9 = findViewById(R.id.accountLogo9);
        accountLogo9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverCurrentJob.this, driverProfile.class);
                startActivity(intent);
            }
        });

    }
}
