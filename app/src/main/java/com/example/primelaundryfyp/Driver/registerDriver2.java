package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class registerDriver2 extends AppCompatActivity {

    private EditText addressDriverReg, icNum;
    private Button signupDriverReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private String email, password, name, phoneNumber, address, icNumber, LicenseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_driver2);

        addressDriverReg = findViewById(R.id.addressDriverReg);
        icNum = findViewById(R.id.icNum);
        signupDriverReg = findViewById(R.id.signupDriverReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        ArrayList<String> driverReg = intent.getStringArrayListExtra("driverReg");

        signupDriverReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                address = addressDriverReg.getText().toString();
                icNumber = icNum.getText().toString();
                driverReg.add(address);
                driverReg.add(icNumber);

                email = driverReg.get(0); // Email is at index 0
                password = driverReg.get(1); // Password is at index 1
                name = driverReg.get(2); // Name is at index 2
                phoneNumber = driverReg.get(3); // Phone number is at index 3
                address = driverReg.get(4); // Address is at index 4
                LicenseNumber = driverReg.get(5);


                firebaseAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(registerDriver2.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                        Map<String, Object> userinfo = new HashMap<>();
                        userinfo.put("Name", name);
                        userinfo.put("PhoneNumber", phoneNumber);
                        userinfo.put("Address", addressDriverReg.getText().toString());
                        userinfo.put("ICNum", icNum.getText().toString());
                        userinfo.put("LicenseNumber", LicenseNumber);
                        userinfo.put("User Type", "Driver");
                        df.set(userinfo);

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                        //Toast.makeText(registerDriver2.this, "Error!"+e, Toast.LENGTH_LONG).show();

                    }  }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerDriver2.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }

                });
             }
        });
    }
}
