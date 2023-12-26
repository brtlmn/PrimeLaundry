package com.example.primelaundryfyp.Model;

public class bookingModel {

    private String dryCleaning, fold, washDry, iron, PickupDate, DeliveryDate, PickupTime, DeliveryTime ;
    public bookingModel(){

    }

    public bookingModel(String dryCleaning, String fold, String washDry, String iron, String PickupDate, String DeliveryDate, String PickupTime, String DeliveryTime){
        this.dryCleaning = dryCleaning;
        this.fold = fold;
        this.washDry = washDry;
        this.iron = iron;
        this.PickupDate = PickupDate;
        this.DeliveryDate = DeliveryDate;
        this.PickupTime = PickupTime;
        this.DeliveryTime = DeliveryTime;

    }

    public String getDryCleaning() {
        return dryCleaning;
    }

    public String getFold() {
        return fold;
    }

    public String getWashDry() {
        return washDry;
    }

    public String getIron() {
        return iron;
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
}
