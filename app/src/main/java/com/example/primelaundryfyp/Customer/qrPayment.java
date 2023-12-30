package com.example.primelaundryfyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.primelaundryfyp.LandingPage.homepageCustomer;
import com.example.primelaundryfyp.R;

public class qrPayment extends AppCompatActivity {

    private ImageView bookingLogo5 ,primeLaundryLogoHome5, historyLogo5,statusLogo5, accountLogo6;
    private Button uploadReceipt;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_payment);

//        uploadReceipt = findViewById(R.id.uploadReceipt);
//        uploadReceipt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Open the file picker
//                openFilePicker();
//            }
//        });

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

//        private void openFilePicker() {
//            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//            intent.setType("/"); // All files
//            startActivityForResult(intent, PICK_FILE_REQUEST);
//        }
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (resultCode == RESULT_OK) {
//                if (requestCode == PICK_FILE_REQUEST) {
//                    if (data != null) {
//                        fileUri = data.getData();
//                        // Upload the file to Firebase Storage
//                        uploadFileToFirebaseStorage(fileUri);
//                    }
//                } else if (requestCode == REQUEST_CAPTURE_IMAGE) {
//                    // Handle the captured image (if needed)
//                    // You can use the fileUri to access the captured image
//                }
//            }
//        }
//
//        private void uploadFileToFirebaseStorage(Uri fileUri) {
//            if (fileUri != null) {
//                try {
//                    InputStream inputStream = getContentResolver().openInputStream(fileUri);
//                    String fileName = "receipt_" + UUID.randomUUID().toString();
//                    StorageReference fileRef = storageReference.child("uploads/" + fileName);
//
//                    fileRef.putStream(inputStream)
//                            .addOnSuccessListener(taskSnapshot -> {
//                                // File uploaded successfully, get the download URL
//                                fileRef.getDownloadUrl().addOnSuccessListener(uri -> {
//                                    // Save the download URL to Firestore or perform other actions
//                                    saveDownloadUrlToFirestore(uri.toString());
//                                });
//                            })
//                            .addOnFailureListener(e -> {
//                                // Handle unsuccessful upload
//                                Log.e("FirebaseStorage", "Upload failed: " + e.getMessage());
//                            });
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        private void saveDownloadUrlToFirestore(String downloadUrl) {
//            FirebaseUser currentUser = Fauth.getCurrentUser();
//            if (currentUser != null) {
//                FirebaseFirestore db = FirebaseFirestore.getInstance();
//                CollectionReference usersCollection = db.collection("Users");
//
//                // Update the document with the download URL
//                DocumentReference userDoc = usersCollection.document(currentUser.getUid());
//                userDoc.update("receiptUrl", downloadUrl)
//                        .addOnSuccessListener(aVoid -> {
//                            // Document updated successfully
//                            Log.d("Firestore", "Download URL saved to Firestore");
//                            Toast.makeText(pay_now.this, "Receipt uploaded successfully!", Toast.LENGTH_SHORT).show();
//                        })
//                        .addOnFailureListener(e -> {
//                            // Handle unsuccessful update
//                            Log.e("Firestore", "Error updating document: " + e.getMessage());
//                            Toast.makeText(pay_now.this, "Failed to upload receipt", Toast.LENGTH_SHORT).show();
//                        });
//            }
        }

}


