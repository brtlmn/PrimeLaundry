package com.example.primelaundryfyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
public class registerDriver2 extends AppCompatActivity {

    private EditText addressDriverReg, icNum;
    private Button signupDriverReg;
    private FirebaseService firebaseService;
    private String email, password, name, phoneNumber, address, icNumber, LicenseNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_driver2);

        firebaseService = new FirebaseService();
        addressDriverReg = findViewById(R.id.addressDriverReg);
        icNum = findViewById(R.id.icNum);
        signupDriverReg = findViewById(R.id.signupDriverReg);
        FirebaseApp.initializeApp(this);
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


                User userModel = new User();
                userModel.setName(name);
                userModel.setEmail(email);
                userModel.setPassword(password);
                userModel.setAddress(address);
                userModel.setPhone_number(phoneNumber);
                userModel.setUser_type(new Constant().TYPE_DRIVER);

                firebaseService.addUser(userModel, new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(registerDriver2.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(registerDriver2.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });
             }
        });
    }
}
