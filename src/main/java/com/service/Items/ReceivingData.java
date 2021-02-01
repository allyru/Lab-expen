package com.service.Items;

public class ReceivingData {

    private String name;
    private float price;
    private int amount;
    private String datePurchase;
    private String dateTimeCreate;


    public ReceivingData(String name, float price, String datePurchase, int amount, String dateTimeCreate) {
        this.name = name;
        this.price = price;
        this.datePurchase = datePurchase;
        this.amount = amount;
        this.dateTimeCreate = dateTimeCreate;
    }

    public ReceivingData() {
        this.name = "";
        this.price = 0;
        this.datePurchase = "";
        this.amount = 0;
        this.dateTimeCreate = "";
    }

    public String getName() { return name; }

    public float getPrice() { return price; }

    public int getAmount() { return amount; }

    public String getDatePurchase() { return datePurchase;  }

    public String getDateTimeCreate() { return dateTimeCreate; }

}
