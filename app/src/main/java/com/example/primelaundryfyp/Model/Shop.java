package com.example.primelaundryfyp.Model;

public class Shop {

    public String ic_num, ssm_number, user_id;
    public Shop() {
    }
    public Shop(String ic_num, String ssm_number, String user_id) {
        this.ic_num = ic_num;
        this.ssm_number = ssm_number;
        this.user_id = user_id;
    }

    public String getIc_num() {
        return ic_num;
    }

    public void setIc_num(String ic_num) {
        this.ic_num = ic_num;
    }

    public String getSsm_number() {
        return ssm_number;
    }

    public void setSsm_number(String ssm_number) {
        this.ssm_number = ssm_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
