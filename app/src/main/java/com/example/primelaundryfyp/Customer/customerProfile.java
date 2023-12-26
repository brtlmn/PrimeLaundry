package com.example.primelaundryfyp.Customer;

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

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
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
import java.util.Map;
import java.util.HashMap;

public class customerProfile extends AppCompatActivity {

    private TextView name, emailCall, customerProfile;
    private Button saveEditProfileCust;
    private EditText phoneNumEditText, addressEditText;
    private ImageView primeLaundryLogoHome4, bookingLogo4;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);


        name = findViewById(R.id.name);
        emailCall = findViewById(R.id.emailCall);
        phoneNumEditText = findViewById(R.id.phoneNumEditText);
        addressEditText = findViewById(R.id.addressEditText);
        saveEditProfileCust = findViewById(R.id.saveEditProfileCust);
        customerProfile = findViewById(R.id.customerProfile);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        user = firebaseAuth.getCurrentUser();



        //Retrieve the data from database and set to this page
        DocumentReference documentReference = firebasefirestore.collection("Users").document(user.getUid());
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                String userPhoneNum = documentSnapshot.getString("PhoneNumber");
                String userAddress = documentSnapshot.getString("Address");
                String userType = documentSnapshot.getString("User Type");
                name.setText(documentSnapshot.getString("Name"));
                emailCall.setText(user.getEmail());
                phoneNumEditText.setText(userPhoneNum);
                addressEditText.setText(userAddress);
                customerProfile.setText(userType);
            }



        });

        saveEditProfileCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditText.getText().toString();
                String address = addressEditText.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("PhoneNumber", pNum);
                edit.put("Address", address);
                df.update(edit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(customerProfile.this, "Save Successful", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(customerProfile.this, "Save unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        primeLaundryLogoHome4 = findViewById(R.id.primeLaundryLogoHome4);
        primeLaundryLogoHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, homepageCustomer.class);
                startActivity(intent);
            }
        });

        bookingLogo4 = findViewById(R.id.bookingLogo4);
        bookingLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, booking.class);
                startActivity(intent);
            }
        });

    }
}
