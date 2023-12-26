package com.example.primelaundryfyp.Model;

public class paymentModel {

    private String type, laundryShop, PickupDate, DeliveryDate, PickupTime, DeliveryTime, subTotal, pickupDeliveryFee, tax, total;
    public paymentModel(){

    }

    public paymentModel(String type, String laundryShop, String PickupDate, String DeliveryDate, String PickupTime, String DeliveryTime, String subTotal, String pickupDeliveryFee, String tax, String total){
        this.type = type;
        this.laundryShop = laundryShop;
        this.PickupDate = PickupDate;
        this.DeliveryDate = DeliveryDate;
        this.PickupTime = PickupTime;
        this.DeliveryTime = DeliveryTime;
        this.subTotal = subTotal;
        this.pickupDeliveryFee = pickupDeliveryFee;
        this.tax = tax;
        this.total = total;
    }
    public String getType() {
        return type;
    }

    public String getLaundryShop() {
        return laundryShop;
    }

    public String getPickupDate (){
        return PickupDate;
    }

    public String getDeliveryDate (){
        return DeliveryDate;
    }

    public String getPickupTime (){
        return PickupTime;
    }

    public String getDeliveryTime (){
        return DeliveryTime;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public String getPickupDeliveryFee() {
        return pickupDeliveryFee;
    }

    public String getTax (){
        return tax;
    }

    public String getTotal (){
        return total;
    }

}
