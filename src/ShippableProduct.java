import java.time.LocalDate;

public class ShippableProduct extends Product implements Shippable{
    private double weight;

    public ShippableProduct(String name, double price, int quantity, double weight){
        super(name,price,quantity);
        this.weight=weight;
    }
    public ShippableProduct(){
        super();
        this.weight=0.0;
    }

    public String getName(){
        return this.name;
    }
    public double getWeight(){
        return this.weight;
    }
}
