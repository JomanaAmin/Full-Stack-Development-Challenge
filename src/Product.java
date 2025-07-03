public class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }
    public Product(){
        this("Unnamed",0.0,0);
    } //just made this no arg constructor but it wont be used

}
