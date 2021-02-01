package com.service.Items;

public class Data {
    private long id;
    private String name;
    private float price;
    private String datePurchase;
    private int amount;
    private String dateTimeCreate;
    private String dateTimeInDB;


    public Data(long id, String name, float price, String datePurchase, int amount, String dateTimeCreate, String dateTimeInDB) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.datePurchase = datePurchase;
        this.amount = amount;
        this.dateTimeCreate = dateTimeCreate;
        this.dateTimeInDB = dateTimeInDB;
    }

    public Data() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.datePurchase = "";
        this.amount = 0;
        this.dateTimeCreate = "";
        this.dateTimeInDB =  "";
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(float price){
        this.price = price;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public void setDatePurchase(String datePurchase){
        this.datePurchase = datePurchase;
    }


    public void setDateTimeCreate(String dateTimeCreate){
        this.dateTimeCreate = dateTimeCreate;
    }

    public void setDateTimeInDB(String dateTimeInDB){
        this.dateTimeInDB = dateTimeInDB;
    }

    public long getId() { return id; }

    public String getName() { return name; }

    public float getPrice() { return price; }

    public int getAmount() { return amount; }

    public String getDatePurchase() { return datePurchase;  }

    public String getDateTimeCreate() { return dateTimeCreate; }

    public String getDateTimeInDB() { return dateTimeInDB;  }
}
