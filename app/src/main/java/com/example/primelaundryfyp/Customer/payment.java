package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

import java.util.ArrayList;

public class payment extends AppCompatActivity {

    private TextView  typeCall, laundryShopCall, pickupDateCall, deliveryDateCall, pickupTimeCall, deliveryTimeCall, subtotalCall, pickupDeliveryFeeCall, taxCall, total;
    private ImageView primeLaundryLogoHome5, historyLogo5,bookingLogo5, statusLogo5, accountLogo6;
    private Button proceedPayment;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;
    private String allType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        typeCall = findViewById(R.id.typeCall);
        laundryShopCall = findViewById(R.id.laundryShopCall);
        pickupDateCall = findViewById(R.id.pickupDateCall);
        deliveryDateCall = findViewById(R.id.deliveryDateCall);
        pickupTimeCall = findViewById(R.id.pickupTimeCall);
        deliveryTimeCall = findViewById(R.id.deliveryTimeCall);
        subtotalCall = findViewById(R.id.subtotalCall);
        pickupDeliveryFeeCall = findViewById(R.id.pickupDeliveryFeeCall);
        taxCall = findViewById(R.id.taxCall);
        total = findViewById(R.id.total);
        proceedPayment = findViewById(R.id.proceedPayment);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        user = firebaseAuth.getCurrentUser();
        ArrayList<String> booking = intent.getStringArrayListExtra("booking");


        DocumentReference documentReference = firebasefirestore.collection("Users").document(user.getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                ArrayList<String> types = new ArrayList<String>();
                types.add(documentSnapshot.getString("dryCleaning"));
                types.add(documentSnapshot.getString("fold"));
                types.add(documentSnapshot.getString("washDry"));
                types.add(documentSnapshot.getString("iron"));
                types.removeIf(String::isEmpty);
                allType = String.join(", ", types);

                typeCall.setText(allType);
                laundryShopCall.setText(documentSnapshot.getString("shopName"));
                pickupDateCall.setText(documentSnapshot.getString("PickupDate"));
                deliveryDateCall.setText(documentSnapshot.getString("DeliveryDate"));
                pickupTimeCall.setText(documentSnapshot.getString("PickupTime"));
                deliveryTimeCall.setText(documentSnapshot.getString("DeliveryTime"));
                subtotalCall.setText(String.valueOf(documentSnapshot.getDouble("subTotal")));
                pickupDeliveryFeeCall.setText(String.valueOf(documentSnapshot.getDouble("pickupDeliveryFee")));
                taxCall.setText(String.valueOf(documentSnapshot.getDouble("tax")));
                total.setText(String.valueOf(documentSnapshot.getDouble("total")));
            }
        });


        primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
        primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo5 = findViewById(R.id.historyLogo5);
        historyLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo5 = findViewById(R.id.statusLogo5);
        statusLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo6 = findViewById(R.id.accountLogo6);
        accountLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, customerProfile.class);
                startActivity(intent);
            }
        });

        proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(payment.this, qrPayment.class);
                intent.putExtra("booking", booking); //recheck semula sebab confuse??
                startActivity(intent);
                proceedPayment();
            }
        });
    }

    private void proceedPayment() {
        Toast.makeText(this, "PROCEED PAYMENT button clicked!", Toast.LENGTH_SHORT).show();
    }
}
