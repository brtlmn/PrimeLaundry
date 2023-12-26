package com.example.primelaundryfyp.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class registerShop extends AppCompatActivity {

    private EditText nameShopReg, emailShopReg, passwordShopReg, phoneNumShopReg, ssmNumber;
    private Button continueShopReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private ArrayList<String> shopReg = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_laundry_shop);

        nameShopReg = findViewById(R.id.nameShopReg);
        emailShopReg = findViewById(R.id.emailShopReg);
        passwordShopReg = findViewById(R.id.passwordShopReg);
        phoneNumShopReg = findViewById(R.id.passwordShopReg);
        ssmNumber = findViewById(R.id.ssmNumber);
        continueShopReg = findViewById(R.id.continueShopReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = firebasefirestore.getInstance();

        continueShopReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameShopReg.getText().toString();
                String email = emailShopReg.getText().toString();
                String password = passwordShopReg.getText().toString();
                String phoneNumber = phoneNumShopReg.getText().toString();
                String SSMNumber = ssmNumber.getText().toString();
                shopReg.add(name);
                shopReg.add(email);
                shopReg.add(password);
                shopReg.add(phoneNumber);
                shopReg.add(SSMNumber);

                Intent intent = new Intent(registerShop.this, registerShop2.class);
                intent.putExtra("shopReg",shopReg);
                startActivity(intent);
            }
        });
    }
}




