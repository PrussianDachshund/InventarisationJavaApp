package com.example.po_projekt1.klasy_inwentaryzacja;

public class Product {
    private String name;
    private float price;
    private int amount;
    private boolean available;

    public Product(float your_price, int your_amount, String name) {
        this.price = your_price;
        this.amount = your_amount;
        this.available = your_amount > 0;
        this.name = name;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setAvailable(int your_amount) {
        this.available = your_amount > 0;
    }
}


