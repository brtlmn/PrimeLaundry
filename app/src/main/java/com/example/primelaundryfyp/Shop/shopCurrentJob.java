package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.Driver.driverJobInfo;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.R;

public class shopCurrentJob extends AppCompatActivity {


    private ImageView primeLaundryLogoHome10, historyLogo10, bookingLogo10, accountLogo11;
    private Button doneShop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_current_job);




        primeLaundryLogoHome10 = findViewById(R.id.primeLaundryLogoHome10);
        primeLaundryLogoHome10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, homepageShop.class);
                startActivity(intent);
            }
        });

        historyLogo10 = findViewById(R.id.historyLogo10);
        historyLogo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo10 = findViewById(R.id.bookingLogo10);
        bookingLogo10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopCurrentJob.class);
                startActivity(intent);
            }
        });


        accountLogo11 = findViewById(R.id.accountLogo11);
        accountLogo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopCurrentJob.this, shopProfile.class);
                startActivity(intent);
            }
        });
    }
}
