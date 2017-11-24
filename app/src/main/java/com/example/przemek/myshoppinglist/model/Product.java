package com.example.przemek.myshoppinglist.model;

/**
 * Created by Przemek on 23.11.2017.
 */

public class Product {
    private String name;
    private int quantity;
    private double price;
    private boolean isChecked;

    public Product() {
    }

    public Product(String name, int quantity, double price, boolean isChecked) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
