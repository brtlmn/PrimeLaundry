package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.R;

public class driverJobInfo extends AppCompatActivity {

    private ImageView primeLaundryLogoHome2, historyLogo2, bookingLogo2, accountLogo3;
    private Button acceptance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_job_info);

        primeLaundryLogoHome2 = findViewById(R.id.primeLaundryLogoHome2);
        primeLaundryLogoHome2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo2 = findViewById(R.id.historyLogo2);
        historyLogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo2 = findViewById(R.id.bookingLogo2);
        bookingLogo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverJobInfo.class);
                startActivity(intent);
            }
        });

        accountLogo3 = findViewById(R.id.accountLogo3);
        accountLogo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverProfile.class);
                startActivity(intent);
            }
        });


        acceptance = findViewById(R.id.acceptance);
        acceptance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverJobInfo.this, driverCurrentJob.class);
                startActivity(intent);
            }
        });
    }
}
