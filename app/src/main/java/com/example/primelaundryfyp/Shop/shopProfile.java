package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class shopProfile extends AppCompatActivity {

    private TextView nameCall, emailCall, ssmNumCall, icNumCall;

    private ImageView primeLaundryLogoHome11, historyLogo11, bookingLogo11, accountLogo12;
    private Button saveEditProfileShop;
    private EditText phoneNumEditTextShop, addressEditTextShop;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser shop;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_profile);

        firebaseService = new FirebaseService();
        nameCall = findViewById(R.id.nameCall);
        emailCall = findViewById(R.id.emailCall);
        phoneNumEditTextShop = findViewById(R.id.phoneNumEditTextShop);
        addressEditTextShop = findViewById(R.id.addressEditTextShop);
        saveEditProfileShop = findViewById(R.id.saveEditProfileShop);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        shop = firebaseAuth.getCurrentUser(); //semak sini

        firebaseService.getUser(shop.getUid(), new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                User user = model.toObject(User.class);
                nameCall.setText(user.getName());
                emailCall.setText(user.getEmail());
                phoneNumEditTextShop.setText(user.getPhone_number());
                addressEditTextShop.setText(user.getAddress());
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        saveEditProfileShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditTextShop.getText().toString();
                String address = addressEditTextShop.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(shop.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("phone_number", pNum);
                edit.put("address", address);
                df.update(edit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(shopProfile.this, "Save Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(shopProfile.this, "Save unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        primeLaundryLogoHome11 = findViewById(R.id.primeLaundryLogoHome11);
        primeLaundryLogoHome11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopProfile.this, homepageShop.class);
                startActivity(intent);
            }
        });

        historyLogo11 = findViewById(R.id.historyLogo11);
        historyLogo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopProfile.this, shopHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo11 = findViewById(R.id.bookingLogo11);
        bookingLogo11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopProfile.this, shopCurrentJob.class);
                startActivity(intent);
            }
        });


        accountLogo12 = findViewById(R.id.accountLogo12);
        accountLogo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(shopProfile.this, shopProfile.class);
                startActivity(intent);
            }
        });
    }
}
