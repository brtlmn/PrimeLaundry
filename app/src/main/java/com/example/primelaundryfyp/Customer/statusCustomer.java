package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;

public class statusCustomer extends AppCompatActivity {

    private CardView cardViewListOfBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_status);

        cardViewListOfBooking = findViewById(R.id.cardViewListOfBooking);
        cardViewListOfBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(statusCustomer.this, viewStatusCustomer.class);
                startActivity(intent);
            }
        });
    }
}
