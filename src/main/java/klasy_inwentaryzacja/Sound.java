package klasy_inwentaryzacja;

public class Sound extends Product{
    private final float power;
    private final float speaker_size;

    Sound(float your_price, int your_amount, String name, float power, float speaker_size) {
        super(your_price, your_amount, name);
        this.power = power;
        this.speaker_size = speaker_size;
    }

    public float getPower() {
        return power;
    }

    public float getSpeaker_size() {
        return speaker_size;
    }
}
