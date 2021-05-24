package models;

public class Product {
    private int id;
    private String name;
    private int barcode;
    private String description;
    private double price;
    private int maxStock;
    private int minStock;
    private int quantity;

    public Product(int id, String name, int barcode, String description, double price, int maxStock, int minStock, int quantity) {
        this.id = id;
        this.name = name;
        this.barcode = barcode;
        this.description = description;
        this.price = price;
        this.maxStock = maxStock;
        this.setMinStock(minStock);
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMaxStock() {
        return maxStock;
    }

    public void setMaxStock(int maxStock) {
        this.maxStock = maxStock;
    }

    public int getMinStock() {
        return minStock;
    }

    public void setMinStock(int minStock) {
        // Value validation
        if (minStock <= 0) {
            throw new IllegalArgumentException("minStock cannot be a value lower than 1.");
        }

        this.minStock = minStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String[] toStringArray() {
        return new String[]{String.valueOf(id), name, String.valueOf(barcode), description, String.valueOf(price), String.valueOf(maxStock), String.valueOf(minStock), String.valueOf(quantity)};
    }
}
