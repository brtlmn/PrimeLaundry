package com.example.primelaundryfyp;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.example.primelaundryfyp.LandingPage.homepageAdmin;
import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.LandingPage.homepageShop;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.Shop.shopProfile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.FirebaseApp;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText emailLogin, password;
    private TextView linkSignup, linkForgotPassword;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailLogin = findViewById(R.id.emailLogin);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        firebaseAuth = FirebaseAuth.getInstance();
        firebasefirestore = FirebaseFirestore.getInstance();
        FirebaseApp.initializeApp(this);
        linkSignup = findViewById(R.id.linkSignup);
        linkForgotPassword = findViewById(R.id.linkForgotPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = emailLogin.getText().toString().trim();
                String Password = password.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            firebasefirestore.collection("Users").document(firebaseAuth.getCurrentUser().getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    String userType = documentSnapshot.getString("user_type");
                                    if (userType != null) {
                                        if (userType.equals(new Constant().TYPE_CUSTOMER)) {
                                            setUICustomer();
                                        } else if (userType.equals(new Constant().TYPE_DRIVER)) {
                                            setUIDriver();
                                        } else if (userType.equals(new Constant().TYPE_SHOP)) {
                                            setUIShop();
                                        } else if (userType.equals(new Constant().TYPE_ADMIN)) {
                                            setUIAdmin();
                                        }
                                    } else {
                                        // Handle the case where "UserType" is null
                                        Toast.makeText(MainActivity.this, "UserType is null", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "Cannot Login. Please check your Email or Password" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, registerOption.class);
                startActivity(intent);
            }
        });

        linkForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, forgotPassword.class));
            }
        });
    }

    private void setUICustomer() {
        Intent intent = new Intent(MainActivity.this, homepageCustomer.class);
        startActivity(intent);
        finish();
    }

    private void setUIDriver() {
        Intent intent = new Intent(MainActivity.this, homepageDriver.class);
        startActivity(intent);
        finish();
    }

    private void setUIShop() {
        Intent intent = new Intent(MainActivity.this, homepageShop.class);
        startActivity(intent);
        finish();
    }

    private void setUIAdmin() {
        Intent intent = new Intent(MainActivity.this, homepageAdmin.class);
        startActivity(intent);
        finish();
    }
}
