package klasy_inwentaryzacja;

public final class Amplifier extends Sound{
    private int id_amp;
    private int channels_amount;
    private String type;
    private boolean effect_loop;

    Amplifier(float your_price, int your_amount, String name) {
        super(your_price, your_amount, name);
    }
}
