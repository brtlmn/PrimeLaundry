package com.example.primelaundryfyp.Customer;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.Constant;
import com.example.primelaundryfyp.FirebaseService;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.Model.User;
import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class scheduleCollection extends AppCompatActivity {

    private EditText pickupDate, deliveryDate, pickupTime, deliveryTime, bookingLogo;
    private ImageView  bookingLogo4, primeLaundryLogoHome4, historyLogo4 ,statusLogo4 , accountLogo5;
    private Button payment;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebasefirestore;
    private FirebaseUser user;
    private String dryCleaning, fold, washDry, iron, PickupDate, DeliveryDate, PickupTime, DeliveryTime ;
    private Double subTotal, deliveryFee, serviceTax, total;
    private String shopId, bookingId;

    private FirebaseService firebaseService;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_collection);

        firebaseService = new FirebaseService();

        primeLaundryLogoHome4 = findViewById(R.id.primeLaundryLogoHome4);
        primeLaundryLogoHome4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleCollection.this, homepageCustomer.class);
                startActivity(intent);
            }
        });


        historyLogo4 = findViewById(R.id.historyLogo4);
        historyLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleCollection.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo4 = findViewById(R.id.bookingLogo4);
        bookingLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleCollection.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo4 = findViewById(R.id.statusLogo4);
        statusLogo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleCollection.this, statusCustomer.class);
                startActivity(intent);
            }
        });

        accountLogo5 = findViewById(R.id.accountLogo5);
        accountLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(scheduleCollection.this, customerProfile.class);
                startActivity(intent);
            }
        });


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

        firebaseService.getShopByName(shopName, new FirebaseService.RetrievalListener<List<DocumentSnapshot>>() {
            @Override
            public void onRetrieved(List<DocumentSnapshot> model) {
                shopId = model.get(0).getId();
            }
            @Override
            public void onNotFound() {
                Toast.makeText(scheduleCollection.this, "Shop not found", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailRetrieval(Exception e) {
                Toast.makeText(scheduleCollection.this, "Shop not found", Toast.LENGTH_SHORT).show();
            }
        });

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

                deliveryFee = 2.0;
                subTotal = 0.0;
                for (String type: booking){
                    if (type.equals("DRY CLEANING")){
                        dryCleaning = "Dry Cleaning";
                        subTotal += 3.8;
                    }
                    if (type.equals("FOLD")){
                        fold = "Fold";
                        subTotal += 1.5;
                    }
                    if (type.equals("WASH AND DRY")){
                        washDry = "Wash and Dry";
                        subTotal += 3.5;
                    }
                    if (type.equals("IRON")){
                        iron = "Iron";
                        subTotal += 1.8;
                    }
                }

                serviceTax = subTotal * 0.06;
                total = serviceTax + subTotal + deliveryFee;

                bookingId = UUID.randomUUID().toString();
                Booking booking = new Booking();
                booking.setId(bookingId);
                booking.setCustomer_id(user.getUid());
                booking.setDriver_id(null);
                booking.setShop_id(shopId);
                booking.setTotal(String.format("%.2f", total));
                booking.setIs_DryCleaning(dryCleaning);
                booking.setIs_fold(fold);
                booking.setIs_washDry(washDry);
                booking.setIs_iron(iron);
                booking.setPickup_date(PickupDate);
                booking.setDelivery_date(DeliveryDate);
                booking.setPickup_time(PickupTime);
                booking.setDelivery_time(DeliveryTime);
                booking.setShop_name(shopName);
                booking.setSub_total(String.format("%.2f", subTotal));
                booking.setDelivery_fee(String.format("%.2f", deliveryFee));
                booking.setTax(String.format("%.2f", serviceTax));
                booking.setStatus(new Constant().STATUS_PENDING);
                firebaseService.addBooking(booking, new FirebaseService.FirebaseListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(scheduleCollection.this, "Booking Successful", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFail(Exception e) {
                        Toast.makeText(scheduleCollection.this, "Booking unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                });

                Intent intent = new Intent(scheduleCollection.this, com.example.primelaundryfyp.Customer.payment.class);
                intent.putExtra("bookingId", bookingId);
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



