package com.example.po_projekt1.klasy_inwentaryzacja;

public final class Amplifier extends Sound{
    private final int channels_amount;
    private final String type;
    private final boolean effect_loop;

    public Amplifier(float your_price, int your_amount, String name, float power, float speaker_size, int channels_amount,
                     String type, boolean effect_loop) {
        super(your_price, your_amount, name, power,speaker_size);
        this.channels_amount = channels_amount;
        this.type = type;
        this.effect_loop = effect_loop;
    }

    public String getType() {
        return type;
    }

    public int getChannels_amount() {
        return channels_amount;
    }

    public boolean isEffect_loop() {
        return effect_loop;
    }
}
