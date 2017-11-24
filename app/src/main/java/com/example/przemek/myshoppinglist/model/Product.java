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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (quantity != product.quantity) return false;
        if (Double.compare(product.price, price) != 0) return false;
        if (isChecked != product.isChecked) return false;
        return name != null ? name.equals(product.name) : product.name == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (isChecked ? 1 : 0);
        return result;
    }
}
