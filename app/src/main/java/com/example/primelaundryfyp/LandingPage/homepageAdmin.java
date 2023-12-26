package com.example.primelaundryfyp.LandingPage;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.R;

public class homepageAdmin extends AppCompatActivity {

    private ImageView customerLogo,driverLogo, shopLogo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
    }

//    customerLogo = findViewById(R.id.customerLogo);
//        customerLogo.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Intent intent = new Intent(userList.this, customerProfile.class);
//            startActivity(intent);
//        }
//    });
}
