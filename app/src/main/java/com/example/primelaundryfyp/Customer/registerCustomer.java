package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;

public class registerCustomer extends AppCompatActivity {

    private EditText nameCustReg, emailCustReg, passwordCustReg, phoneNumCustReg, addressCustReg;
    private Button signupCustReg;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_customer);

        firebaseService = new FirebaseService();

        nameCustReg = findViewById(R.id.nameCustReg);
        emailCustReg = findViewById(R.id.emailCustReg);
        passwordCustReg = findViewById(R.id.passwordCustReg);
        phoneNumCustReg = findViewById(R.id.phoneNumCustReg);
        addressCustReg = findViewById(R.id.addressCustReg);
        signupCustReg = findViewById(R.id.signupCustReg);
        FirebaseApp.initializeApp(this);

        signupCustReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailCustReg.getText().toString();
                String password = passwordCustReg.getText().toString();
                final String name = nameCustReg.getText().toString();
                final String phoneNumber = phoneNumCustReg.getText().toString();
                final String address = addressCustReg.getText().toString();

                User newUser = new User();
                newUser.setName(name);
                newUser.setEmail(email);
                newUser.setPassword(password);
                newUser.setAddress(address);
                newUser.setPhone_number(phoneNumber);
                newUser.setUser_type(newUser.TYPE_CUSTOMER);
                firebaseService.addUser(newUser, new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(registerCustomer.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(registerCustomer.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
