import java.time.LocalDate;

public class ExpirableProduct extends Product implements Expirable{
    private LocalDate expiryDate;

    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate){
        super(name,price,quantity);
        this.expiryDate=expiryDate;
    }
    public ExpirableProduct(){
        super();
        this.expiryDate=null;
    }


    public String getName(){
        return this.name;
    }

    public LocalDate getExpiryDate(){
        return expiryDate;
    }
}
