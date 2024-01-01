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

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
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
import java.util.Map;
import java.util.HashMap;

public class customerProfile extends AppCompatActivity {

    private TextView name, emailCall, customerProfile;
    private Button saveEditProfileCust;
    private EditText phoneNumEditText, addressEditText;
    private ImageView primeLaundryLogoHome4,historyLogo4, bookingLogo4, statusLogo4, accountLogo5;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_profile);

        firebaseService = new FirebaseService();
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

        firebaseService.getUser(user.getUid(), new FirebaseService.RetrievalListener<DocumentSnapshot>() {
            @Override
            public void onRetrieved(DocumentSnapshot model) {
                User user = model.toObject(User.class);
                name.setText(user.getName());
                emailCall.setText(user.getEmail());
                phoneNumEditText.setText(user.getPhone_number());
                addressEditText.setText(user.getAddress());
                customerProfile.setText(user.getUser_type());
            }

            @Override
            public void onNotFound() {

            }

            @Override
            public void onFailRetrieval(Exception e) {

            }
        });

        saveEditProfileCust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditText.getText().toString();
                String address = addressEditText.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("phone_number", pNum);
                edit.put("address", address);
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


        historyLogo4 = findViewById(R.id.historyLogo4);
        historyLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, history.class);
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


        bookingLogo4 = findViewById(R.id.bookingLogo4);
        bookingLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo4 = findViewById(R.id.statusLogo4);
        statusLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo5 = findViewById(R.id.accountLogo5);
        accountLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(customerProfile.this, customerProfile.class);
                startActivity(intent);
            }
        });


    }
}
