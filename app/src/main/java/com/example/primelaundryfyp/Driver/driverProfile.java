package com.example.primelaundryfyp.Driver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private Button saveEditProfileDriver;
    private EditText phoneNumEditTextDriver, addressEditTextDriver;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser driver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_profile);

        nameCall = findViewById(R.id.nameCall);
        emailCall = findViewById(R.id.emailCall);
        phoneNumEditTextDriver = findViewById(R.id.phoneNumEditTextDriver);
        addressEditTextDriver = findViewById(R.id.addressEditTextDriver);
        licenseNumCall = findViewById(R.id.licenseNumCall);
        icNumCall = findViewById(R.id.icNumCall);
        saveEditProfileDriver = findViewById(R.id.saveEditProfileDriver);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        driver = firebaseAuth.getCurrentUser();

        DocumentReference documentReference = firebasefirestore.collection("Users").document(driver.getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                String userPhoneNum = documentSnapshot.getString("PhoneNumber");
                String userAddress = documentSnapshot.getString("Address");
                nameCall.setText(documentSnapshot.getString("Name"));
                emailCall.setText(driver.getEmail());
                phoneNumEditTextDriver.setText(userPhoneNum);
                addressEditTextDriver.setText(userAddress);
                licenseNumCall.setText(documentSnapshot.getString("LicenseNumber"));
                icNumCall.setText(documentSnapshot.getString("ICNum"));
            }
        });

        saveEditProfileDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditTextDriver.getText().toString();
                String address = addressEditTextDriver.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(driver.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("PhoneNumber", pNum);
                edit.put("Address", address);
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
    }
}
