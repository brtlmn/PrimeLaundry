package com.example.primelaundryfyp.LandingPage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Customer.customerList;
import com.example.primelaundryfyp.R;
import com.example.primelaundryfyp.Shop.shopList;

public class homepageAdmin extends AppCompatActivity {

    private ImageView customerLogo, driverLogo, shopLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_admin);

        customerLogo = findViewById(R.id.customerLogo);
        customerLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageAdmin.this, customerList.class);
                startActivity(intent);
            }
        });

        driverLogo = findViewById(R.id.driverLogo);
        driverLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(homepageAdmin.this, driverList.class);
//                startActivity(intent);
            }
        });

        shopLogo = findViewById(R.id.shopLogo);
        shopLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepageAdmin.this, shopList.class);
                startActivity(intent);
            }
        });
    }
}
