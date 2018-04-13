public class Product {

    private int name;
    private int price;
    private double quantity;
    private double quality;
    private double hplus;
    private double pmm;
    private double awesome;

    public Product(){
        this.name = 0;
        this.price = 0;
        this.quantity = 0;
        this.quality = 0;
        this.hplus = 0;
        this.pmm = 0;
        this.awesome = 0;
    }

    public Product(int name, int price, double quantity, double quality, double hplus, double pmm, double awesome){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.quality = quality;
        this.hplus = hplus;
        this.pmm = pmm;
        this.awesome = awesome;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
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

    public double getHplus() {
        return hplus;
    }

    public void setHplus(double hplus) {
        this.hplus = hplus;
    }

    public double getPmm() {
        return pmm;
    }

    public void setPmm(double pmm) {
        this.pmm = pmm;
    }

    public double getAwesome() {
        return awesome;
    }

    public void setAwesome(double awesome) {
        this.awesome = awesome;
    }

}