package com.example.po_projekt1.klasy_inwentaryzacja;

public final class Keys extends Product{
    private String type;
    private int keys_amount;
    private float weight;
    private float speaker_size;
    private float speaker_power;

    public Keys(float your_price, int your_amount, String name, String type, int keys_amount, float weight,
                float speaker_power, float speaker_size) {
        super(your_price, your_amount, name);
        this.type=type;
        this.keys_amount=keys_amount;
        this.weight=weight;
        this.speaker_power=speaker_power;
        this.speaker_size=speaker_size;
    }

    public String getType() {
        return type;
    }

    public float getSpeaker_power() {
        return speaker_power;
    }

    public float getSpeaker_size() {
        return speaker_size;
    }

    public float getWeight() {
        return weight;
    }

    public int getKeys_amount() {
        return keys_amount;
    }

}
