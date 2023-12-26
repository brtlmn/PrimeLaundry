package com.example.primelaundryfyp.Driver;

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
public class registerDriver extends AppCompatActivity {

    private EditText nameDriverReg, emailDriverReg, passwordDriverReg, phoneNumDriverReg, licenseNumber;
    private Button continueDriverReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private ArrayList<String> driverReg = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_driver);

        nameDriverReg = findViewById(R.id.nameDriverReg);
        emailDriverReg = findViewById(R.id.emailDriverReg);
        passwordDriverReg = findViewById(R.id.passwordDriverReg);
        phoneNumDriverReg = findViewById(R.id.phoneNumDriverReg);
        licenseNumber = findViewById(R.id.licenseNumber);
        continueDriverReg = findViewById(R.id.continueDriverReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = firebasefirestore.getInstance();


        continueDriverReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailDriverReg.getText().toString();
                String password = passwordDriverReg.getText().toString();
                String name = nameDriverReg.getText().toString();
                String phoneNumber = phoneNumDriverReg.getText().toString();
                String LicenseNumber = licenseNumber.getText().toString();
                driverReg.add(email);
                driverReg.add(password);
                driverReg.add(name);
                driverReg.add(phoneNumber);
                driverReg.add(LicenseNumber);

                Intent intent = new Intent(registerDriver.this, registerDriver2.class);
                intent.putExtra("driverReg",driverReg);
                startActivity(intent);
            }
        });
    }
}

