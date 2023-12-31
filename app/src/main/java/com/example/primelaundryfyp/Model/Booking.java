package com.example.primelaundryfyp.Model;

public class Booking {
    private String id, status;
    private String customer_id, driver_id, shop_id, total, is_DryCleaning, is_fold, is_washDry, is_iron, pickup_date, delivery_date, pickup_time, delivery_time, shop_name, sub_total, delivery_fee, tax;
    public Booking() {
    }

    public Booking(String id, String customer_id, String driver_id, String shop_id, String total, String is_DryCleaning, String is_fold, String is_washDry, String is_iron, String pickup_date, String delivery_date, String pickup_time, String delivery_time, String shop_name, String sub_total, String delivery_fee, String tax, String status) {
        this.customer_id = customer_id;
        this.driver_id = driver_id;
        this.shop_id = shop_id;
        this.total = total;
        this.is_DryCleaning = is_DryCleaning;
        this.is_fold = is_fold;
        this.is_washDry = is_washDry;
        this.is_iron = is_iron;
        this.pickup_date = pickup_date;
        this.delivery_date = delivery_date;
        this.pickup_time = pickup_time;
        this.delivery_time = delivery_time;
        this.shop_name = shop_name;
        this.sub_total = sub_total;
        this.delivery_fee = delivery_fee;
        this.tax = tax;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getIs_DryCleaning() {
        return is_DryCleaning;
    }

    public void setIs_DryCleaning(String is_DryCleaning) {
        this.is_DryCleaning = is_DryCleaning;
    }

    public String getIs_fold() {
        return is_fold;
    }

    public void setIs_fold(String is_fold) {
        this.is_fold = is_fold;
    }

    public String getIs_washDry() {
        return is_washDry;
    }

    public void setIs_washDry(String is_washDry) {
        this.is_washDry = is_washDry;
    }

    public String getIs_iron() {
        return is_iron;
    }

    public void setIs_iron(String is_iron) {
        this.is_iron = is_iron;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public void setPickup_date(String pickup_date) {
        this.pickup_date = pickup_date;
    }

    public String getDelivery_date() {
        return delivery_date;
    }

    public void setDelivery_date(String delivery_date) {
        this.delivery_date = delivery_date;
    }

    public String getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(String pickup_time) {
        this.pickup_time = pickup_time;
    }

    public String getDelivery_time() {
        return delivery_time;
    }

    public void setDelivery_time(String delivery_time) {
        this.delivery_time = delivery_time;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getSub_total() {
        return sub_total;
    }

    public void setSub_total(String sub_total) {
        this.sub_total = sub_total;
    }

    public String getDelivery_fee() {
        return delivery_fee;
    }

    public void setDelivery_fee(String delivery_fee) {
        this.delivery_fee = delivery_fee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }
}
