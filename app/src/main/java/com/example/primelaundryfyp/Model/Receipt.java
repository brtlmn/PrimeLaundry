package com.example.primelaundryfyp.Model;

public class Receipt {

    String id, user_id, url, booking_id;

    public Receipt() {
    }

    public Receipt(String id, String user_id, String url, String booking_id) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
        this.booking_id = booking_id;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
