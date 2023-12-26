package com.example.primelaundryfyp.Shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class registerShop2 extends AppCompatActivity {

    private EditText addressShopReg, icNum;
    private Button signupShopReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private String name, email, password, phoneNumber, ssmNumber, address, icNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_laundry_shop2);

        addressShopReg = findViewById(R.id.addressShopReg);
        icNum = findViewById(R.id.icNum);
        signupShopReg = findViewById(R.id.signupShopReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        ArrayList<String> shopReg = intent.getStringArrayListExtra("shopReg");

        signupShopReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                address = addressShopReg.getText().toString();
                icNumber = icNum.getText().toString();
                shopReg.add(address);
                shopReg.add(icNumber);

                name = shopReg.get(0);
                email = shopReg.get(1);
                password = shopReg.get(2);
                ssmNumber = shopReg.get(3);
                phoneNumber = shopReg.get(4);
                address = shopReg.get(5);


                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(registerShop2.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                        Map<String, Object> userinfo = new HashMap<>();
                        userinfo.put("Name", name);
                        userinfo.put("SSMNumber", ssmNumber);
                        userinfo.put("PhoneNumber", phoneNumber);
                        userinfo.put("Address", addressShopReg.getText().toString());
                        userinfo.put("ICNum", icNum.getText().toString());
                        userinfo.put("User Type", "Shop");
                        df.set(userinfo);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        //Toast.makeText(registerDriver2.this, "Error!"+e, Toast.LENGTH_LONG).show();

                    }  }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerShop2.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }

                });
            }
        });
    }

}
