package com.example.primelaundryfyp.Model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class User {

    public final String TYPE_CUSTOMER = "customer";
    public final String TYPE_SHOP = "shop";
    public final String TYPE_DRIVER = "driver";
    public final String TYPE_ADMIN = "admin";
    private String user_id, address, name, phone_number, user_type, password, email;

    public User(){

    }
    public User(String user_id, String address, String name, String phone_number, String user_type, String password, String email){
        this.address = address;
        this.name = name;
        this.phone_number = phone_number;
        this.user_type = user_type;
        this.user_id = user_id;
        this.password = password;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getUserType() {
        return user_type;
    }

    public String getUserId() {return user_id;}

    public  String getPassword(){return password;}

    public String getEmail() {return email;}

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
