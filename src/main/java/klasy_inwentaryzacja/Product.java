package klasy_inwentaryzacja;

public class Product {
    private static int count = 0;
    private int id;
    private float price;
    private int amount;
    private String name;
    private boolean available;

    Product(float your_price, int your_amount, String name) {
        this.id = count++;
        this.price = your_price;
        this.amount = your_amount;
        this.available = your_amount > 0;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isAvailable() {
        return available;
    }
}


