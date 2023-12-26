package com.example.primelaundryfyp.Model;

public class adminModel {

        private String Name, PhoneNumber, UserType;

        public adminModel(){

        }
        public adminModel(String Name, String PhoneNumber, String UserType){
            this.Name = Name;
            this.PhoneNumber = PhoneNumber;
            this.UserType = UserType;

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

