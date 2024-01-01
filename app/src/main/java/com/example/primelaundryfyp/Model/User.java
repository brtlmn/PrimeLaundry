package com.example.primelaundryfyp.Model;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class User {
    private String id, address, name, phone_number, user_type, password, email;

    public User(){

    }
    public User(String id, String address, String name, String phone_number, String user_type, String email){
        this.address = address;
        this.name = name;
        this.phone_number = phone_number;
        this.user_type = user_type;
        this.id = id;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getUser_type() {
        return user_type;
    }

    public String getId() {return id;}

    public  String getPassword(){return password;}

    public String getEmail() {return email;}

    public void setId(String user_id) {
        this.id = user_id;
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
