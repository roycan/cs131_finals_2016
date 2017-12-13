public class Product {

    private int price;
    private double quantity;
    private double quality;

    public Product(){
        this.price = 0;
        this.quantity = 0;
        this.quality = 0;
    }

    public Product(int price, double quantity, double quality){
        this.price = price;
        this.quantity = quantity;
        this.quality = quality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getQuality() {
        return quality;
    }

    public void setQuality(double quality) {
        this.quality = quality;
    }

}