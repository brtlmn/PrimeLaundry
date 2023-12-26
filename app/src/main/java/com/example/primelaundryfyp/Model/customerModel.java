package com.example.primelaundryfyp.Model;

public class customerModel {

    private String Address, Name, PhoneNumber, UserType;

    public customerModel(){

    }
    public customerModel(String Address, String Name, String PhoneNumber, String UserType){
        this.Address = Address;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
        this.UserType = UserType;

    }

    public String getAddress() {
        return Address;
    }

    public String getName() {
        return Name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getUserType() {
        return UserType;
    }
}
