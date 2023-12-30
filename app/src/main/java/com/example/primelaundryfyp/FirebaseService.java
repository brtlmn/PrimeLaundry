package com.example.primelaundryfyp;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.primelaundryfyp.Customer.registerCustomer;
import com.example.primelaundryfyp.Model.Shop;
import com.example.primelaundryfyp.Model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.core.FirestoreClient;

import java.util.HashMap;
import java.util.Map;

public class FirebaseService {
    public interface UsersCallback {
        void onUsersLoaded(HashMap<String, User> usersMap);
        void onUsersLoadFailure(Exception e);
    }

    public interface FirebaseListener {
        void onSuccess();
        void onFail(Exception e);
    }

    private FirebaseFirestore firestore;
    private FirebaseAuth firebaseAuth;

    public FirebaseService() {
        this.firestore = FirebaseFirestore.getInstance();
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public void getAllUsers(String collectionName, UsersCallback callback) {
        CollectionReference usersCollection = firestore.collection(collectionName);
        HashMap<String, User> usersMap = new HashMap<>();

        usersCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String userId = document.getId();
                    User user = document.toObject(User.class);
                    usersMap.put(userId, user);
                }
                callback.onUsersLoaded(usersMap);
            } else {
                callback.onUsersLoadFailure(task.getException());
            }
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
}
