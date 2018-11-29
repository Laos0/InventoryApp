package com.example.android.myinventoryapp;

public class Inventory {
    private int id;
    private String name;
    private int quantity;
    private String supplier;
    private double price;

    public Inventory(){

    }

    public Inventory(int id, String name, int quantity, String supplier, double price){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.supplier = supplier;
        this.price = price;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setSupplier(String supplier){
        this.supplier = supplier;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getSupplier(){
        return supplier;
    }

    public double getPrice(){
        return price;
    }
}
