package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.Model.customerModel;
import com.example.primelaundryfyp.Driver.driverProfile;
import com.example.primelaundryfyp.Driver.driverStatus;
import com.example.primelaundryfyp.Driver.pickupDelivery;
import com.example.primelaundryfyp.LandingPage.homepageDriver;
import com.example.primelaundryfyp.Model.shopModel;
import com.example.primelaundryfyp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
public class booking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CheckBox dryCleaningCheckBox, foldCheckBox, washDryCheckBox, ironCheckBox;
    private ImageView primeLaundryLogoHome4, historyLogo4, bookingLogo4, statusLogo4, accountLogo5;
    private Button scheduleCollection;
    private Spinner laundryShop;
    private GoogleMap mapView;
    private List<String> userList;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> booking = new ArrayList<>();
    private String userType = "Shop";
    private String shopName;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laundry_booking);

        // Initialize Firebase
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        dryCleaningCheckBox = findViewById(R.id.dryCleaningCheckBox);
        foldCheckBox = findViewById(R.id.foldCheckBox);
        washDryCheckBox = findViewById(R.id.washDryCheckBox);
        ironCheckBox = findViewById(R.id.ironCheckBox);
        scheduleCollection = findViewById(R.id.scheduleCollection);
        laundryShop = findViewById(R.id.laundryShop);

        userList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,userList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        laundryShop.setAdapter(adapter);

        laundryShop.setOnItemSelectedListener(this);

        // Read data from Firebase
        readDataFromFirebase();

//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.mapView, mapFragment)
//                .commit();
//        mapFragment.getMapAsync(this);

        scheduleCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dryCleaning = dryCleaningCheckBox.getText().toString();
                String fold = foldCheckBox.getText().toString();
                String washDry = washDryCheckBox.getText().toString();
                String iron = ironCheckBox.getText().toString();

                // CheckBox validation
                if (!(dryCleaningCheckBox.isChecked() || foldCheckBox.isChecked() || washDryCheckBox.isChecked() || ironCheckBox.isChecked())) {
                    Toast.makeText(booking.this, "Please choose the laundry service", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dryCleaningCheckBox.isChecked()) {
                    booking.add(dryCleaning);
                }

                if (foldCheckBox.isChecked()) {
                    booking.add(fold);
                }

                if (washDryCheckBox.isChecked()) {
                    booking.add(washDry);
                }

                if (ironCheckBox.isChecked()) {
                    booking.add(iron);
                }

                Intent intent = new Intent(booking.this, com.example.primelaundryfyp.Customer.scheduleCollection.class);
                intent.putExtra("booking", booking);
                intent.putExtra("shopName", shopName);
                startActivity(intent);
            }
        });

        // Initialize logos
        primeLaundryLogoHome4 = findViewById(R.id.primeLaundryLogoHome4);
        historyLogo4 = findViewById(R.id.historyLogo4);
        bookingLogo4 = findViewById(R.id.bookingLogo4);
        statusLogo4 = findViewById(R.id.statusLogo4);
        accountLogo5 = findViewById(R.id.accountLogo5);

        // Set click listeners for logos
        primeLaundryLogoHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        historyLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, homepageDriver.class);
                startActivity(intent);
            }
        });

        bookingLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, pickupDelivery.class);
                startActivity(intent);
            }
        });

        statusLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, driverStatus.class);
                startActivity(intent);
            }
        });

        accountLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, driverProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Retrieve the selected item using parent.getItemAtPosition(position)
        shopName = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle situation when nothing is selected (if needed)
    }

    private void readDataFromFirebase() {
        CollectionReference usersCollection = firebaseFirestore.collection("Users");

        usersCollection.whereEqualTo("userType", new Constant().TYPE_SHOP)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userList.clear();
                        for (DocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            if (user != null) {
                                String userName = user.getName();
                                userList.add(userName);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle the error if needed
                        // For example, you can log the error
                        Log.e("Firestore Error", "Error getting documents: ", task.getException());
                    }
                });
    }
}
