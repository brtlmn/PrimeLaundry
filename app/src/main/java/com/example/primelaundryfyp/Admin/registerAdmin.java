package com.example.primelaundryfyp.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;

public class registerAdmin extends AppCompatActivity {

    private EditText nameAdminReg, emailAdminReg, passwordAdminReg, phoneNumAdminReg;
    private Button signupAdminReg;
    private FirebaseService firebaseService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_admin);

        firebaseService = new FirebaseService();
        nameAdminReg = findViewById(R.id.nameAdminReg);
        emailAdminReg = findViewById(R.id.emailAdminReg);
        passwordAdminReg = findViewById(R.id.passwordAdminReg);
        phoneNumAdminReg = findViewById(R.id.phoneNumAdminReg);
        signupAdminReg = findViewById(R.id.signupAdminReg);
        FirebaseApp.initializeApp(this);

        signupAdminReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAdminReg.getText().toString();
                String password = passwordAdminReg.getText().toString();
                final String name = nameAdminReg.getText().toString();
                final String phoneNumber = phoneNumAdminReg.getText().toString();

                User userModel = new User();
                userModel.setName(name);
                userModel.setEmail(email);
                userModel.setPassword(password);
                userModel.setAddress(null);
                userModel.setPhone_number(phoneNumber);
                userModel.setUser_type(userModel.TYPE_ADMIN);

                firebaseService.addUser(userModel, new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(registerAdmin.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(registerAdmin.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
