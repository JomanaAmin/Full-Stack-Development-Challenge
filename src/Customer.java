import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Customer {
    private double balance;
    private Cart cart;
    private String name;

    public Customer(String name, double balance, Cart cart){
        this.name=name;
        this.balance=balance;
        this.cart=cart;
    }
    public Customer(){
        this("Unknown",0.0,new Cart());
    }
    public Customer(String name, double balance){

        this(name,balance,new Cart());
    }

    public Cart getCart(){
        return this.cart;
    }
    public double getBalance(){
        return this.balance;
    }




    public void checkout() {
        if (cart == null || cart.isEmpty()) {
            System.out.println("Cart is empty! Add items before checking out.");
            return;
        }
//        if (cart.hasInvalidQuantities()) {
//            System.out.println("Invalid quantities. Update quantities before checkout.");
//            return;
//        }
        ShippingService ship=new ShippingService();

        try{
            this.cart.checkAllProductsStock();
            this.cart.checkExpiryDates();
            double shippingFee=0;

            HashMap<Shippable,Integer> shippableItems=cart.getShippableItems();
            if (!shippableItems.isEmpty()){
                ship=new ShippingService(shippableItems);
                shippingFee=ship.getShippingFee();

            }
            double subtotal=this.cart.getTotal();
            double total=subtotal+shippingFee;
            if (total>this.balance){
                throw new IllegalStateException("Insufficient amount in your balance.");
            }
            this.balance-=total;
            System.out.println("==================================");
            System.out.println("CHECKOUT...");
            if(this.cart.hasShippables())
                ship.displayShippingDetails();
            else
                System.out.println("No items to be shipped.");
            System.out.println("==================================");

            this.cart.displayCartItems();

            System.out.println("==================================");
            System.out.println("Subtotal\t"+subtotal);
            System.out.println("Shipping\t"+shippingFee);
            System.out.println("Amount\t"+total);
            System.out.println("Remaining balance: "+this.balance);
            System.out.println("Ordered by: "+this.name);

            cart.reduceStock();
            cart.clear();
            System.out.println("==================================");

        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }

    }

}

