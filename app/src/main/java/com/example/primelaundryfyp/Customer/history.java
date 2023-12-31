package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.primelaundryfyp.Adapter.CustomerHistoryAdapter;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class history extends AppCompatActivity{

        private ImageView primeLaundryLogoHome5, historyLogo5, bookingLogo5, statusLogo5, accountLogo6;
        private FirebaseAuth firebaseAuth;
        private FirebaseFirestore firebasefirestore;
        private FirebaseUser user;
        private FirebaseService firebaseService;

        private RecyclerView recyclerView;
        CustomerHistoryAdapter adapter;
        ArrayList<Booking> items;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.order_history_customer);

            items = new ArrayList<>();
            firebaseService = new FirebaseService();
            firebaseAuth = FirebaseAuth.getInstance();
            FirebaseApp.initializeApp(this);
            firebasefirestore = FirebaseFirestore.getInstance();
            user = firebaseAuth.getCurrentUser();


            firebaseService.getBookingsByCustomer(user.getUid(), new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
                @Override
                public void onRetrieved(List<DocumentSnapshot> model) {
                    for (DocumentSnapshot m : model){
                        Booking booking = m.toObject(Booking.class);
                        items.add(booking);
                    }

                    recyclerView = findViewById(R.id.cycleView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(history.this));
                    adapter = new CustomerHistoryAdapter(history.this, items);
                    recyclerView.setAdapter(adapter);
                }

                @Override
                public void onNotFound() {

                }

                @Override
                public void onFailRetrieval(Exception e) {

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
                    Intent intent = new Intent(history.this, statusCustomer.class);
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
