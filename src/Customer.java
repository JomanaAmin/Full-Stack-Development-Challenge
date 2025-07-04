import java.util.HashMap;

public class Customer {
    private double balance;
    private Cart cart= new Cart();
    private String name;

    public Customer(String name, double balance, Cart cart){
        this.name=name;
        this.balance=balance;
        this.cart=cart;
    }
    public Customer(){
        this("Unknown",0.0,null);
    }

    public void purchase() throws IllegalStateException{
        if (cart.getTotal()>this.balance){
            throw new IllegalStateException("Insufficient amount in your balance.");
        }

    }
}
