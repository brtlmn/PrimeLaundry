package com.example.primelaundryfyp.Driver;

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
import com.example.primelaundryfyp.LandingPage.homepageDriver;
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

public class driverProfile extends AppCompatActivity {

    private TextView nameCall, emailCall, licenseNumCall, icNumCall;

    private ImageView primeLaundryLogoHome12, historyLogo12, bookingLogo12, accountLogo13;
    private Button saveEditProfileDriver;
    private EditText phoneNumEditTextDriver, addressEditTextDriver;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser driver;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        nameCall = findViewById(R.id.nameCall);
        emailCall = findViewById(R.id.emailCall);
        phoneNumEditTextDriver = findViewById(R.id.phoneNumEditTextDriver);
        addressEditTextDriver = findViewById(R.id.addressEditTextDriver);
        saveEditProfileDriver = findViewById(R.id.saveEditProfileDriver);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        driver = firebaseAuth.getCurrentUser();
        firebaseService = new FirebaseService();

        firebaseService.getUser(driver.getUid(), new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                User user = model.toObject(User.class);
                nameCall.setText(user.getName());
                emailCall.setText(user.getEmail());
                phoneNumEditTextDriver.setText(user.getPhone_number());
                addressEditTextDriver.setText(user.getAddress());
//                licenseNumCall.setText(documentSnapshot.getString("LicenseNumber"));
//                icNumCall.setText(documentSnapshot.getString("ICNum"));
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        saveEditProfileDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditTextDriver.getText().toString();
                String address = addressEditTextDriver.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(driver.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("phone_number", pNum);
                edit.put("address", address);
                df.update(edit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(driverProfile.this, "Save Successful", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(driverProfile.this, "Save unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        primeLaundryLogoHome12 = findViewById(R.id.primeLaundryLogoHome12);
        primeLaundryLogoHome12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverProfile.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo12 = findViewById(R.id.historyLogo12);
        historyLogo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverProfile.this, driverJobHistory.class);
                startActivity(intent);
            }
        });

        bookingLogo12 = findViewById(R.id.bookingLogo12);
        bookingLogo12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverProfile.this, driverJobInfo.class);
                startActivity(intent);
            }
        });

        accountLogo13 = findViewById(R.id.accountLogo13);
        accountLogo13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(driverProfile.this, driverProfile.class);
                startActivity(intent);
            }
        });
    }
}
