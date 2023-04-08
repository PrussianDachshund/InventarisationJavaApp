package klasy_inwentaryzacja;

public final class Percussive extends Product{
    private int id_percussive;
    private String material;
    private String type;
    private float size;

    Percussive(float your_price, int your_amount, String name) {
        super(your_price, your_amount, name);
    }
}
