import java.util.ArrayList;
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

    public Cart getCart(){
        return this.cart;
    }

    public void checkout() throws IllegalStateException{
        if (cart==null || cart.isEmpty()){
            throw new IllegalStateException("Cart is empty! Add items before checking out.");
        }

        double total=cart.getTotal();
        if (total>this.balance){
            throw new IllegalStateException("Insufficient amount in your balance.");
        }
        this.balance-=total;
        HashMap<Shippable,Integer> shippableItems=cart.getShippableItems();
        if (!shippableItems.isEmpty()){
            ShippingService ship=new ShippingService(shippableItems);
        }
    }
}
