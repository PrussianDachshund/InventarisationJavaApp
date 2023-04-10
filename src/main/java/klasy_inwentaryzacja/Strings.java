package klasy_inwentaryzacja;

public final class Strings extends Product{
    private int id_string;
    private String type;
    private String material;
    private int fret_amount;
    private String colour;
    private int strings_amount;

    public Strings(float your_price, int your_amount, String name, String type, String material, int fret_amount,
                   int strings_amount, String colour) {
        super(your_price, your_amount, name);
        this.type = type;
        this.material = material;
        this.fret_amount = fret_amount;
        this.colour = colour;
        this.strings_amount=strings_amount;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getFret_amount() {
        return fret_amount;
    }

    public String getColour() {
        return colour;
    }

    public int getStrings_amount() {
        return strings_amount;
    }
}




