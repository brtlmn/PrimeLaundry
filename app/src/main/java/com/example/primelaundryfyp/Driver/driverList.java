//package com.example.primelaundryfyp.Driver;
//
//import android.os.Bundle;
//import android.widget.ArrayAdapter;
//import android.widget.ListView;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.primelaundryfyp.R;
//import com.google.firebase.FirebaseApp;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.firestore.ChildEventListener;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.DatabaseError;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//import java.util.ArrayList;
//
//public class DriverList extends AppCompatActivity {
//
//
//
//    private FirebaseAuth firebaseAuth;
//    private FirebaseFirestore firebasefirestore;
//    private FirebaseUser user;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.driver_list);
//
//
//        DocumentReference documentreference;
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseApp.initializeApp(this);
//        firebasefirestore = FirebaseFirestore.getInstance();
//        user = firebaseAuth.getCurrentUser();
//        ListView listView;
//        ArrayList<String> arrayList = new ArrayList<>();
//        ArrayAdapter<String> arrayAdapter;
//
//
////        DocumentReference = FirebaseFirestore.getInstance().getReference("Users");
//        DocumentReference documentReference = firebasefirestore.collection("Users").document(user.getUid());
//
//        listView = findViewById(R.id.driverList);
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);
//        listView.setAdapter(arrayAdapter);
//
//        documentReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DocumentSnapshot documentSnapshot, String s) {
//                // Handle new child added to the database
//                String value = documentSnapshot.getValue(String.class);
//                arrayList.add(value);
//                arrayAdapter.notifyDataSetChanged(); // Notify the adapter that the data has changed
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DocumentSnapshot documentSnapshot,String s) {
//                // Handle a change in the existing child
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DocumentSnapshot documentSnapshot) {
//                // Handle a child being removed from the database
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DocumentSnapshot documentSnapshot, String s) {
//                // Handle a change in the order of children
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                // Handle errors
//            }
//        });
//    }
//}
