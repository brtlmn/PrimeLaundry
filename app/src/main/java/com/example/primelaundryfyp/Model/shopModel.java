package com.example.primelaundryfyp.Model;

public class shopModel {
    private String Name, Address, PhoneNumber, SSMNumber, ICNum, UserType;

    public shopModel(){

    }
    public shopModel(String Name, String Address, String PhoneNumber, String SSMNumber, String ICNum, String UserType){
        this.Name = Name;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
        this.SSMNumber = SSMNumber;
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

    public String getSSMNumber(){
        return SSMNumber;
    }

    public String getICNum(){
        return ICNum;
    }

    public String getUserType(){
        return UserType;
    }
}

