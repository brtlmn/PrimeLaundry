package com.example.primelaundryfyp.Customer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.Model.Receipt;
import com.example.primelaundryfyp.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class qrPayment extends AppCompatActivity {

    private ImageView bookingLogo5 ,primeLaundryLogoHome5, historyLogo5,statusLogo5, accountLogo6;
    private Button uploadReceipt;
    private String userId, bookingId;
    StorageReference storageReference;
    FirebaseFirestore firestore;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_payment);

        uploadReceipt = findViewById(R.id.uploadReceipt);

        storageReference = FirebaseStorage.getInstance().getReference();
        firestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseApp.initializeApp(this);
        user = firebaseAuth.getCurrentUser();
        userId = user.getUid();
        Intent intent = getIntent();
        bookingId = intent.getStringExtra("bookingId");

        uploadReceipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFiles();
            }
        });

        primeLaundryLogoHome5 = findViewById(R.id.primeLaundryLogoHome5);
        primeLaundryLogoHome5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPayment.this, homepageCustomer.class);
                startActivity(intent);
            }
        });

        historyLogo5 = findViewById(R.id.historyLogo5);
        historyLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPayment.this, history.class);
                startActivity(intent);
            }
        });

        bookingLogo5 = findViewById(R.id.bookingLogo5);
        bookingLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPayment.this, booking.class);
                startActivity(intent);
            }
        });


        statusLogo5 = findViewById(R.id.statusLogo5);
        statusLogo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPayment.this, statusCustomer.class);
                startActivity(intent);
            }
        });


        accountLogo6 = findViewById(R.id.accountLogo6);
        accountLogo6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(qrPayment.this, customerProfile.class);
                startActivity(intent);
            }
        });
    }

    private void selectFiles() {
        Intent intent = new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select files"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            UploadFiles(data.getData());
        }
    }

    private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading...");
        progressDialog.show();

        StorageReference reference = storageReference.child("Uploads/" + System.currentTimeMillis() + ".pdf");
        reference.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!uriTask.isComplete());
                        Uri url = uriTask.getResult();

                        Receipt receipt = new Receipt(UUID.randomUUID().toString(), userId, url.toString(), bookingId);
                        DocumentReference receiptsRef = firestore.collection("Receipts").document(receipt.getId());
                        receiptsRef.set(receipt);

                        Toast.makeText(qrPayment.this, "Receipt uploaded", Toast.LENGTH_SHORT).show();

                        progressDialog.dismiss();

                        Intent intent = new Intent(qrPayment.this, statusCustomer.class);
                        startActivity(intent);

                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        double progress = (100.0* snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                        progressDialog.setMessage("Uploaded:"+(int) progress+"%");
                    }
                });
    }
}


