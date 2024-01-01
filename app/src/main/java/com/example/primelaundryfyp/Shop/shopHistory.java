package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.R;

public class shopHistory extends AppCompatActivity {

        private ImageView primeLaundryLogoHome10, historyLogo10, bookingLogo10, accountLogo11;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.shop_history);



            primeLaundryLogoHome10 = findViewById(R.id.primeLaundryLogoHome10);
            primeLaundryLogoHome10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopHistory.this, homepageShop.class);
                    startActivity(intent);
                }
            });

            historyLogo10 = findViewById(R.id.historyLogo10);
            historyLogo10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopHistory.this, shopHistory.class);
                    startActivity(intent);
                }
            });

            bookingLogo10 = findViewById(R.id.bookingLogo10);
            bookingLogo10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopHistory.this, shopCurrentJob.class);
                    startActivity(intent);
                }
            });


            accountLogo11 = findViewById(R.id.accountLogo11);
            accountLogo11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopHistory.this, shopProfile.class);
                    startActivity(intent);
                }
            });

        }
    }



