package com.example.primelaundryfyp;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.primelaundryfyp.Customer.registerCustomer;
import com.example.primelaundryfyp.Model.Booking;
import com.example.primelaundryfyp.Model.Shop;
import com.example.primelaundryfyp.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseService {
    public interface FirebaseListener {
        void onSuccess();
        void onFail(Exception e);
    }

    public interface RetrievalListener<T> {
        void onRetrieved(T model);
        void onNotFound();
        void onFailRetrieval(Exception e);

    }

    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;

    public FirebaseService() {
        this.firestore = FirebaseFirestore.getInstance();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void getCustomers(RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Users");

        collectionReference.whereEqualTo("user_type", new User().TYPE_CUSTOMER)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getDrivers(RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Users");

        collectionReference.whereEqualTo("user_type", new User().TYPE_DRIVER)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getShops(RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Users");

        collectionReference.whereEqualTo("user_type", new User().TYPE_SHOP)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void addUser(User user, FirebaseListener listener) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                DocumentReference usersCollection = firestore.collection("Users").document(firebaseUser.getUid());
                Map<String, Object> userData = new HashMap<>();
                userData.put("name", user.getName());
                userData.put("address", user.getAddress());
                userData.put("phone_number", user.getPhoneNumber());
                userData.put("user_type", user.getUserType());
                usersCollection.set(userData)
                        .addOnSuccessListener(aVoid -> {
                            listener.onSuccess();
                        })
                        .addOnFailureListener(e -> {
                            listener.onFail(e);
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onFail(e);
            }
        });
    }

    public void addUserWithShop(User user, Shop shop,  FirebaseListener firebaseListener) {
        firebaseAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                DocumentReference usersCollection = firestore.collection("Users").document(firebaseUser.getUid());
                Map<String, Object> userData = new HashMap<>();
                userData.put("name", user.getName());
                userData.put("address", user.getAddress());
                userData.put("phone_number", user.getPhoneNumber());
                userData.put("user_type", user.getUserType());
                usersCollection.set(userData);

                DocumentReference shopCollection = firestore.collection("Shops").document(firebaseUser.getUid());
                Map<String, Object> shopInfo = new HashMap<>();
                shopInfo.put("ssm_number", shop.getSsm_number());
                shopInfo.put("ic_num", shop.getIc_num());
                shopInfo.put("user_id", firebaseUser.getUid());
                shopCollection.set(shopInfo)
                        .addOnSuccessListener(aVoid -> {
                            firebaseListener.onSuccess();
                        })
                        .addOnFailureListener(e -> {
                            firebaseListener.onFail(e);
                        });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                firebaseListener.onFail(e);
            }
        });
    }

    public void addBooking(Booking bookingModel, FirebaseListener listener) {
        DocumentReference bookingsCollection = firestore.collection("Bookings").document(bookingModel.getId());
        bookingsCollection.set(bookingModel)
                .addOnSuccessListener(aVoid -> {
                    listener.onSuccess();
                })
                .addOnFailureListener(e -> {
                    listener.onFail(e);
                });
    }

    public void getBookings(RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Bookings");

        collectionReference.whereEqualTo("user_type", new User().TYPE_SHOP)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getBookingsByCustomer(String customer_id, RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Bookings");

        collectionReference.whereEqualTo("customer_id", customer_id)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getBookingById(String id, RetrievalListener<DocumentSnapshot> listener) {
        DocumentReference bookingRef = firestore.collection("Bookings").document(id);

        bookingRef.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        listener.onRetrieved(documentSnapshot);
                    } else {
                        listener.onNotFound();
                    }
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getShopByName(String name, RetrievalListener<List<DocumentSnapshot>> listener) {
        CollectionReference collectionReference = firestore.collection("Users");

        collectionReference.whereEqualTo("user_type", new User().TYPE_SHOP)
                .whereEqualTo("name", name)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    listener.onRetrieved(queryDocumentSnapshots.getDocuments());
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }

    public void getUser(String id, RetrievalListener<DocumentSnapshot> listener) {
        DocumentReference bookingRef = firestore.collection("Users").document(id);

        bookingRef.get()
                .addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        listener.onRetrieved(documentSnapshot);
                    } else {
                        listener.onNotFound();
                    }
                })
                .addOnFailureListener(e -> {
                    listener.onFailRetrieval(e);
                });
    }


}
