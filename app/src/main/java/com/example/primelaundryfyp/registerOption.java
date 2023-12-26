package com.example.primelaundryfyp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class registerOption extends AppCompatActivity {

    private Button registerCustomer, registerDriver, registerShop, registerAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_option);

        //linkkan button tu dengan xml pakai ID

        registerCustomer = findViewById(R.id.registerCustomer);
        registerDriver = findViewById(R.id.registerDriver);
        registerShop = findViewById(R.id.registerShop);
        registerAdmin = findViewById(R.id.registerAdmin);

        registerCustomer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(registerOption.this, com.example.primelaundryfyp.Customer.registerCustomer.class));
                    finish();
            }
        });
        registerDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerOption.this, com.example.primelaundryfyp.Driver.registerDriver.class));
                finish();
            }
        });

        registerShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerOption.this, com.example.primelaundryfyp.Shop.registerShop.class));
                finish();
            }
        });


        registerAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(registerOption.this, com.example.primelaundryfyp.Admin.registerAdmin.class));
                finish();
            }
        });
    }

}
