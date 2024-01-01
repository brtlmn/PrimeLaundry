package com.example.primelaundryfyp.Shop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.MainActivity;
import com.example.primelaundryfyp.Model.Shop;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;

public class registerShop2 extends AppCompatActivity {

    private EditText addressShopReg, icNum;
    private Button signupShopReg;
    private FirebaseService firebaseService;
    private String name, email, password, phoneNumber, ssmNumber, address, icNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_laundry_shop2);

        firebaseService = new FirebaseService();
        addressShopReg = findViewById(R.id.addressShopReg);
        icNum = findViewById(R.id.icNum);
        signupShopReg = findViewById(R.id.signupShopReg);
        FirebaseApp.initializeApp(this);
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

                User userModel = new User();
                userModel.setName(name);
                userModel.setEmail(email);
                userModel.setPassword(password);
                userModel.setAddress(address);
                userModel.setPhone_number(phoneNumber);
                userModel.setUser_type(new Constant().TYPE_SHOP);

                Shop shopModel = new Shop();
                shopModel.setIc_num(icNumber);
                shopModel.setSsm_number(ssmNumber);

                firebaseService.addUserWithShop(userModel, shopModel, new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(registerShop2.this, "Account Registered", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(registerShop2.this, "Error!"+e, Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

}
