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
//
//    public void checkout() throws IllegalStateException{
//        if (cart==null || cart.isEmpty()){
//            throw new IllegalStateException("Cart is empty! Add items before checking out.");
//        }
//        ShippingService ship=new ShippingService();
//
//        this.cart.checkExpiryDates();
//        double total=cart.getTotal();
//        if (total>this.balance){
//            throw new IllegalStateException("Insufficient amount in your balance.");
//        }
//        this.balance-=total;
//        HashMap<Shippable,Integer> shippableItems=cart.getShippableItems();
//        if (!shippableItems.isEmpty()){
//            ship=new ShippingService(shippableItems);
//        }
//        System.out.println("CHECKOUT...");
//        ship.displayShippingDetails();
//        System.out.println("==================================");
//
//        this.cart.displayCartItems();
//        double subtotal=this.cart.calculateTotal();
//        double shippingFee=ship.getShippingFee();
//        System.out.println("==================================");
//        System.out.println("Subtotal\t"+subtotal);
//        System.out.println("Shipping\t"+shippingFee);
//        System.out.println("Amount\t"+(shippingFee+subtotal));
//
//        cart.clear();
//    }

    public void checkout() {
        if (cart==null || cart.isEmpty()){
            System.out.println("Cart is empty! Add items before checking out.");
            return;
        }
        ShippingService ship=new ShippingService();

        try{
            this.cart.checkExpiryDates();
            double total=cart.getTotal();
            if (total>this.balance){
                throw new IllegalStateException("Insufficient amount in your balance.");
            }
            this.balance-=total;
            HashMap<Shippable,Integer> shippableItems=cart.getShippableItems();
            if (!shippableItems.isEmpty()){
                ship=new ShippingService(shippableItems);
            }
            System.out.println("CHECKOUT...");
            ship.displayShippingDetails();
            System.out.println("==================================");

            this.cart.displayCartItems();
            double subtotal=this.cart.calculateTotal();
            double shippingFee=ship.getShippingFee();
            System.out.println("==================================");
            System.out.println("Subtotal\t"+subtotal);
            System.out.println("Shipping\t"+shippingFee);
            System.out.println("Amount\t"+(shippingFee+subtotal));

            cart.clear();
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }

    }

}

