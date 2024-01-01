package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.Driver.driverCurrentJob;
import com.example.primelaundryfyp.Driver.driverJobInfo;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.firestore.DocumentSnapshot;

public class shopJobInfo extends AppCompatActivity {

        private ImageView primeLaundryLogoHome10, historyLogo10, bookingLogo10, accountLogo11;
        private Button doneShop;
        private String bookingId;
        private FirebaseService firebaseService;
        private String bookingStatus;
        private TextView customerName, pickupDate, deliveryDate, pickupTime, deliveryTime, total, status;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.shop_job_info);

            firebaseService = new FirebaseService();
            Intent intent = getIntent();
            bookingId = intent.getStringExtra("bookingId");

            customerName = findViewById(R.id.customerName);
            pickupDate = findViewById(R.id.pickupDate);
            deliveryDate = findViewById(R.id.deliveryDate);
            pickupTime = findViewById(R.id.pickupTime);
            deliveryTime = findViewById(R.id.deliveryTime);
            total = findViewById(R.id.total);
            status = findViewById(R.id.status);
            doneShop = findViewById(R.id.doneShop);

            firebaseService.getBookingById(bookingId, new FirebaseService.RetrievalListener<DocumentSnapshot>() {
                @Override
                public void onRetrieved(DocumentSnapshot model) {
                    Booking booking = model.toObject(Booking.class);

                    customerName.setText(booking.getShop_name()); //Check
                    pickupDate.setText(booking.getPickup_date());
                    deliveryDate.setText(booking.getDelivery_date());
                    pickupTime.setText(booking.getPickup_time());
                    deliveryTime.setText(booking.getDelivery_time());
                    total.setText(booking.getTotal());
                    status.setText(booking.getStatus());

                    bookingStatus = booking.getStatus();

                    if (booking.getStatus().equals(new Constant().STATUS_PENDING)) {
                        doneShop.setText("Waiting Driver Pickup");
                    }

                    if (booking.getStatus().equals(new Constant().STATUS_PICKUP)) {
                        doneShop.setText("Done");
                    }

                }

                @Override
                public void onNotFound() {

                }

                @Override
                public void onFailRetrieval(Exception e) {

                }
            });


            primeLaundryLogoHome10 = findViewById(R.id.primeLaundryLogoHome10);
            primeLaundryLogoHome10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopJobInfo.this, homepageShop.class);
                    startActivity(intent);
                }
            });

            historyLogo10 = findViewById(R.id.historyLogo10);
            historyLogo10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopJobInfo.this, shopHistory.class);
                    startActivity(intent);
                }
            });

            bookingLogo10 = findViewById(R.id.bookingLogo10);
            bookingLogo10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopJobInfo.this, shopCurrentJob.class);
                    startActivity(intent);
                }
            });


            accountLogo11 = findViewById(R.id.accountLogo11);
            accountLogo11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(shopJobInfo.this, shopProfile.class);
                    startActivity(intent);
                }
            });

            doneShop = findViewById(R.id.doneShop);
            doneShop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (bookingStatus.equals(new Constant().STATUS_PICKUP)) {

                        firebaseService.updateBookingStatusShop(bookingId, new FirebaseService.FirebaseListener() {
                            @Override
                            public void onSuccess() {
                                Intent intent = new Intent(shopJobInfo.this, shopHistory.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFail(Exception e) {

                            }
                        });
                    }
                }
            });
        }
    }


