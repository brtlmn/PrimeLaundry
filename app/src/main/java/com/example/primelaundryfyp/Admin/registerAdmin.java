package com.example.primelaundryfyp.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Customer.registerCustomer;
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

import java.util.HashMap;
import java.util.Map;

public class registerAdmin extends AppCompatActivity {

    private EditText nameAdminReg, emailAdminReg, passwordAdminReg, phoneNumAdminReg;
    private Button signupAdminReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_admin);


        nameAdminReg = findViewById(R.id.nameAdminReg);
        emailAdminReg = findViewById(R.id.emailAdminReg);
        passwordAdminReg = findViewById(R.id.passwordAdminReg);
        phoneNumAdminReg = findViewById(R.id.phoneNumAdminReg);
        signupAdminReg = findViewById(R.id.signupAdminReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = firebasefirestore.getInstance();

        signupAdminReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAdminReg.getText().toString();
                String password = passwordAdminReg.getText().toString();
                final String name = nameAdminReg.getText().toString();
                final String phoneNumber = phoneNumAdminReg.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(registerAdmin.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                        Map<String, Object> userinfo = new HashMap<>();
                        userinfo.put("Name", nameAdminReg.getText().toString());
                        userinfo.put("PhoneNumber", phoneNumAdminReg.getText().toString());
                        userinfo.put("User Type", "Admin");
                        df.set(userinfo);


                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerAdmin.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
