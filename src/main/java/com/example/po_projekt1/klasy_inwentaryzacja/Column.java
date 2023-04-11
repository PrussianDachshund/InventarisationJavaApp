package com.example.po_projekt1.klasy_inwentaryzacja;

public final class Column extends Sound{
    private final int speakers_amount;
    private final float impedance;
    private final float weight;


    public Column(float your_price, int your_amount, String name, float power, float speaker_size,
                  float impedance, float weight, int speaker_amount) {
        super(your_price, your_amount, name, power, speaker_size);
        this.speakers_amount = speaker_amount;
        this.impedance = impedance;
        this.weight = weight;
    }

    public float getImpedance() {
        return impedance;
    }

    public float getWeight() {
        return weight;
    }

    public int getSpeakers_amount() {
        return speakers_amount;
    }
}
