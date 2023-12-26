package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class history extends AppCompatActivity{

        private TextView laundryShop, pickupDate,deliveryDate,pickupTime, deliveryTime,total;
        private ImageView primeLaundryLogoHome5, historyLogo5, bookingLogo5, statusLogo5, accountLogo6;
        private FirebaseAuth firebaseAuth;
        private FirebaseFirestore firebasefirestore;
        private FirebaseUser user;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.order_history_customer);

            laundryShop = findViewById(R.id.laundryShop);
            pickupDate = findViewById(R.id.pickupDate);
            deliveryDate = findViewById(R.id.deliveryDate);
            pickupTime = findViewById(R.id.pickupTime);
            deliveryTime = findViewById(R.id.deliveryTime);
            total = findViewById(R.id.total);
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseApp.initializeApp(this);
            firebasefirestore = FirebaseFirestore.getInstance();
            user = firebaseAuth.getCurrentUser();


            //Retrieve the data from database and set to this page
            DocumentReference documentReference = firebasefirestore.collection("Users").document(user.getUid());
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    laundryShop.setText(documentSnapshot.getString("Name")); //untuk panggil data dari datanase
                    pickupDate.setText(documentSnapshot.getString("PickupDate"));
                    deliveryDate.setText(documentSnapshot.getString("DeliveryDate"));
                    pickupTime.setText(documentSnapshot.getString("PickupTime"));
                    deliveryTime.setText(documentSnapshot.getString("DeliveryTime"));
                    total.setText(documentSnapshot.getString("total"));
                }
            });

            primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
            primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(history.this, homepageCustomer.class);
                    startActivity(intent);
                }
            });


            historyLogo5 = findViewById(R.id.historyLogo5);
            historyLogo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(history.this, history.class);
                    startActivity(intent);
                }
            });

            bookingLogo5 = findViewById(R.id.bookingLogo5);
            bookingLogo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(history.this, booking.class);
                    startActivity(intent);
                }
            });

            statusLogo5 = findViewById(R.id.statusLogo5);
            statusLogo5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(history.this, status.class);
                    startActivity(intent);
                }
            });

            accountLogo6 = findViewById(R.id.accountLogo6);
            accountLogo6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(history.this, customerProfile.class);
                    startActivity(intent);
                }
            });
        }
}
