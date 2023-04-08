package klasy_inwentaryzacja;

public final class String extends Product{
    private int id_string;
    private String type;
    private String material;
    private int fret_amount;
    private String colour;
    private int strings_amount;

    String(float your_price, int your_amount, String name) {
        super(your_price, your_amount, name);
    }
}
