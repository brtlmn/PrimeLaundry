package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.R;

public class driverJobHistory extends AppCompatActivity {
    private ImageView primeLaundryLogoHome6, historyLogo6, bookingLogo6, accountLogo4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_job_history);

        primeLaundryLogoHome6 = findViewById(R.id.primeLaundryLogoHome6);
        primeLaundryLogoHome6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo6 = findViewById(R.id.historyLogo6);
        historyLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo6 = findViewById(R.id.bookingLogo6);
        bookingLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverJobInfo.class);
                startActivity(intent);
            }
        });


        accountLogo4 = findViewById(R.id.accountLogo4);
        accountLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobHistory.this, driverProfile.class);
                startActivity(intent);
            }
        });

    }

}
