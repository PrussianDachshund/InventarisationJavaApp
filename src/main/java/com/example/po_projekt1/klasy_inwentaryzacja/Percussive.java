package com.example.po_projekt1.klasy_inwentaryzacja;

public final class Percussive extends Product{
    private String material;
    private String type;
    private float size;

    public Percussive(float your_price, int your_amount, String name, String material, String type, float size) {
        super(your_price, your_amount, name);
        this.material = material;
        this.type = type;
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public String getType() {
        return type;
    }

    public float getSize() {
        return size;
    }
}
