package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import com.google.firebase.FirebaseApp;

public class registerCustomer extends AppCompatActivity {

    private EditText nameCustReg, emailCustReg, passwordCustReg, phoneNumCustReg, addressCustReg;
    private Button signupCustReg;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private String userId, userType, name, phoneNumber, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_customer);

        nameCustReg = findViewById(R.id.nameCustReg);
        emailCustReg = findViewById(R.id.emailCustReg);
        passwordCustReg = findViewById(R.id.passwordCustReg);
        phoneNumCustReg = findViewById(R.id.phoneNumCustReg);
        addressCustReg = findViewById(R.id.addressCustReg);
        signupCustReg = findViewById(R.id.signupCustReg);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = firebasefirestore.getInstance();

        signupCustReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailCustReg.getText().toString();
                String password = passwordCustReg.getText().toString();
                final String name = nameCustReg.getText().toString();
                final String phoneNumber = phoneNumCustReg.getText().toString();
                final String address = addressCustReg.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Toast.makeText(registerCustomer.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                        Map<String, Object> userinfo = new HashMap<>();
                        userinfo.put("Name", nameCustReg.getText().toString());
                        userinfo.put("PhoneNumber", phoneNumCustReg.getText().toString());
                        userinfo.put("Address", addressCustReg.getText().toString());
                        userinfo.put("UserType", "Customer");
                        df.set(userinfo);


                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(registerCustomer.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });
                }
                });
            }
        }
