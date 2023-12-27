package com.example.primelaundryfyp.Customer;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class scheduleCollection extends AppCompatActivity {

    private EditText pickupDate, deliveryDate, pickupTime, deliveryTime, bookingLogo;
    private Button payment;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;
    private String dryCleaning, fold, washDry, iron, PickupDate, DeliveryDate, PickupTime, DeliveryTime ;
    private Double subTotal, deliveryFee, serviceTax, total;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_collection);

        pickupDate = findViewById(R.id.pickupDate);
        pickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        deliveryDate = findViewById(R.id.deliveryDate);
        deliveryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });

        pickupTime = findViewById(R.id.pickupTime);
        pickupTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        deliveryTime = findViewById(R.id.deliveryTime);
        deliveryTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog2();
            }
        });

        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        updateTimeEditText(hour, minute);
        updateTimeEditText2(hour, minute);

        payment = findViewById(R.id.payment);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        firebasefirestore = FirebaseFirestore.getInstance();
        Intent intent = getIntent();
        user = firebaseAuth.getCurrentUser();
        ArrayList<String> booking = intent.getStringArrayListExtra("booking");
        String shopName = intent.getStringExtra("shopName");

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PickupDate = pickupDate.getText().toString();
                DeliveryDate = deliveryDate.getText().toString();
                PickupTime = pickupTime.getText().toString();
                DeliveryTime = deliveryTime.getText().toString();

                dryCleaning = "";
                fold = "";
                washDry = "";
                iron = "";

                subTotal = 0.0;
                deliveryFee = 2.0;

                for (String element: booking) {
                    if (element.contains("DRY CLEANING")) {
                        dryCleaning = "DRY CLEANING";
                        subTotal += 3.5;
                    } else if (element.contains("FOLD")) {
                        fold = "FOLD";
                        subTotal += 1.5;
                    } else if (element.contains("WASH AND DRY")){
                        washDry = "WASH AND DRY";
                        subTotal += 2.8;
                    } else if (element.contains("IRON")) {
                        iron = "IRON";
                        subTotal += 1.8;
                    }
                }
                // Service tax 6%
                serviceTax = subTotal * 0.06;
                total = subTotal + deliveryFee + serviceTax;

                DocumentReference df = firebasefirestore.collection("Users").document(user.getUid());
                Map<String, Object> edit = new HashMap<>();
                edit.put("dryCleaning", dryCleaning);
                edit.put("fold", fold);
                edit.put("washDry", washDry);
                edit.put("iron", iron);
                edit.put("PickupDate", PickupDate);
                edit.put("DeliveryDate", DeliveryDate);
                edit.put("PickupTime", PickupTime);
                edit.put("DeliveryTime", DeliveryTime);
                edit.put("shopName", shopName);
                edit.put("subTotal", subTotal);
                edit.put("pickupDeliveryFee", deliveryFee);
                edit.put("tax", serviceTax);
                edit.put("total", total);
                df.update(edit).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(scheduleCollection.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(scheduleCollection.this, "Booking unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });


                Intent intent = new Intent(scheduleCollection.this, com.example.primelaundryfyp.Customer.payment.class);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        updateDateEditText(year, monthOfYear, dayOfMonth);
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void showDatePickerDialog2() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        updateDateEditText2(year, monthOfYear, dayOfMonth);
                    }
                },
                year, month, dayOfMonth);

        datePickerDialog.show();
    }

    private void updateDateEditText(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String selectedDate = dateFormat.format(calendar.getTime());

        pickupDate.setText(selectedDate);
    }

    private void updateDateEditText2(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String selectedDate = dateFormat.format(calendar.getTime());

        deliveryDate.setText(selectedDate);
    }

    private void showTimePickerDialog() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        updateTimeEditText(hourOfDay, minute);
                    }
                },
                hour, minute, true);

        timePickerDialog.show();
    }

    private void updateTimeEditText(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        String selectedTime = dateFormat.format(calendar.getTime());

        pickupTime.setText(selectedTime);
    }

    private void showTimePickerDialog2() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        updateTimeEditText(hourOfDay, minute);
                    }
                },
                hour, minute, true);

        timePickerDialog.show();
    }

    private void updateTimeEditText2(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.US);
        String selectedTime = dateFormat.format(calendar.getTime());

        deliveryTime.setText(selectedTime);
    }



}



