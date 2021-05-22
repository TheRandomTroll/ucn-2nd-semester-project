package models;

public class OrderLine {
    private int quantity;
    private Product product;

    public OrderLine(Product product, int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "<html>" + product.getName() + " (" + quantity + " * " + product.getPrice() + ")<br>" + String.format("%.2f", product.getPrice() * quantity) + "kn";
    }
}
