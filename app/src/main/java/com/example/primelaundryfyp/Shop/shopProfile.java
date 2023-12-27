package com.example.primelaundryfyp.Shop;

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

public class shopProfile extends AppCompatActivity {

    private TextView nameCall, emailCall, ssmNumCall, icNumCall;
    private Button saveEditProfileShop;
    private EditText phoneNumEditTextShop, addressEditTextShop;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_profile);

        nameCall = findViewById(R.id.nameCall);
        emailCall = findViewById(R.id.emailCall);
        phoneNumEditTextShop = findViewById(R.id.phoneNumEditTextShop);
        addressEditTextShop = findViewById(R.id.addressEditTextShop);
        saveEditProfileShop = findViewById(R.id.saveEditProfileShop);
        ssmNumCall = findViewById(R.id.ssmNumCall);
        icNumCall = findViewById(R.id.icNumCall);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        shop = firebaseAuth.getCurrentUser(); //semak sini

        DocumentReference documentReference = firebasefirestore.collection("Users").document(shop.getUid()); //semak sini
        System.out.println(documentReference);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
//                System.out.println(documentSnapshot);
                String userPhoneNum = documentSnapshot.getString("PhoneNumber");
                String userAddress = documentSnapshot.getString("Address"); //untuk edit text
                nameCall.setText("khairil");
//                nameCall.setText(documentSnapshot.getString("Name")); //untuk panggil data dari database
                emailCall.setText(shop.getEmail());
                phoneNumEditTextShop.setText(userPhoneNum);
                addressEditTextShop.setText(userAddress);
                ssmNumCall.setText(documentSnapshot.getString("SSMNumber"));
                icNumCall.setText(documentSnapshot.getString("ICNum"));

            }
        });

        saveEditProfileShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pNum = phoneNumEditTextShop.getText().toString();
                String address = addressEditTextShop.getText().toString();

                DocumentReference df = firebasefirestore.collection("Users").document(shop.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("PhoneNumber", pNum);
                edit.put("Address", address);
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
    }
}
