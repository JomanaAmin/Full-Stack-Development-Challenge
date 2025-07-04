import java.time.LocalDate;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private boolean shippable;
    private boolean expirable;
    private double weight;
    private LocalDate expiryDate;

    public Product(String name, double price, int quantity, boolean shippable, double weight, boolean expirable, LocalDate expiryDate) throws IllegalArgumentException{
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        if (quantity < 0) throw new IllegalArgumentException("Quantity cannot be negative.");
        if (shippable && weight <= 0) throw new IllegalArgumentException("Shippable product must have positive weight.");
        if (expirable && expiryDate == null) throw new IllegalArgumentException("Expirable product must have expiry date.");
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.shippable = shippable;
        this.weight = shippable ? weight : 0.0;
        this.expirable = expirable;
        this.expiryDate = expirable ? expiryDate : null;
    }

    public Product(String name, double price, int quantity) { // extra constructor for non-shippable and non-expirable items
        this(name, price, quantity, false, 0.0, false, null);
    }
    public Product() { // no arg default constructor but not needed
        this("Unnamed", 0.0, 0);
    }

    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public boolean isShippable(){
        return shippable;
    }
    public double getWeight(){
        return weight;
    }
    public boolean isExpirable(){
        return expirable;
    }
    public LocalDate getExpiryDate(){
        return expiryDate;
    }
    public void decrementQuantity() throws IllegalStateException{
        if (quantity <= 0)
            throw new IllegalStateException("Cannot decrement quantity below 0.");
        quantity--;
    }

    public boolean isExpired() {
        return expirable && expiryDate != null && LocalDate.now().isAfter(expiryDate);
    }
}
