package com.example.primelaundryfyp.Model;

public class Receipt {

    String id, user_id, url;

    public Receipt() {
    }

    public Receipt(String id, String user_id, String url) {
        this.id = id;
        this.user_id = user_id;
        this.url = url;
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
