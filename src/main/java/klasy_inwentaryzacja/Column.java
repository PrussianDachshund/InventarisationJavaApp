package klasy_inwentaryzacja;

public final class Column extends Sound{
    private int id_column;
    private int speakers_amount;
    private float impedance;

    private float weight;
    private int inputs_amount;

    Column(float your_price, int your_amount, String name) {
        super(your_price, your_amount, name);
    }
}
