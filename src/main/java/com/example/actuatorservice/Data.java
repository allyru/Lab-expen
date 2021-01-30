package com.example.actuatorservice;

public class Data {
    private long id;
    private String name;
    private float price;
    private String date;
    private int amount;

    public Data(long id, String name, float price, String date, int amount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.date = date;
        this.amount = amount;
    }

    public Data() {
        this.id = 0;
        this.name = "";
        this.price = 0;
        this.date = "";
        this.amount = 0;
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

    public void setDate(String date){
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() { return price; }

    public int getAmount() { return amount; }

    public String getDate() {
        return date;
    }
}
