package com.example.primelaundryfyp.Model;

public class driverModel {

    private String Name, Address, PhoneNumber, LicenseNumber, ICNum, UserType;

    public driverModel(){

    }
    public driverModel(String Name, String Address, String PhoneNumber, String LicenseNumber, String ICNum, String UserType){
        this.Name = Name;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.LicenseNumber = LicenseNumber;
        this.ICNum = ICNum;
        this.UserType = UserType;
    }

    public String getName(){
        return Name;
    }

    public String getAddress(){
        return Address;
    }

    public String getPhoneNumber(){
        return PhoneNumber;
    }

    public String getLicenseNumber(){
        return LicenseNumber;
    }

    public String getICNum(){
        return ICNum;
    }

    public String getUserType(){
        return UserType;
    }
}
