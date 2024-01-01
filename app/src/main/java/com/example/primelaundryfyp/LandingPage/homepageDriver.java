package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.Driver.driverCurrentJob;
import com.example.primelaundryfyp.Driver.driverJobHistory;
import com.example.primelaundryfyp.Driver.driverJobInfo;
import com.example.primelaundryfyp.Driver.driverProfile;
import com.example.primelaundryfyp.R;

public class homepageDriver extends AppCompatActivity {

    private ImageView primeLaundryLogoHome5, historyLogo5, bookingLogo5, accountLogo6;
    private CardView cardViewJobList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_driver);

        cardViewJobList = findViewById(R.id.cardViewJobList);
        cardViewJobList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverJobInfo.class);
                startActivity(intent);
            }
        });

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
                Intent intent = new Intent(homepageDriver.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageDriver.this, driverCurrentJob.class);
                startActivity(intent);
            }
        });


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
