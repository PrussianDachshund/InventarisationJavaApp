package klasy_inwentaryzacja;

public final class Keys extends Product{
    private int id_keys;
    private String type;
    private int keys_amount;
    private float weight;
    private float speaker_size;
    private float speaker_power;

    Keys(float your_price, int your_amount, String name) {
        super(your_price, your_amount, name);
    }
}
