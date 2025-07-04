import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart {

    private HashMap<Product,Integer> cartItems;
    private double total;

    public Cart(HashMap<Product,Integer> cartItems, double total){
        this.cartItems=cartItems;
        this.total=total;
    }

    public Cart(){
        this(new HashMap<Product,Integer>(),0);
    }

    public double getTotal(){
        this.total=0;
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            total+=item.getKey().getPrice()*item.getValue();
        }
        return this.total;
    }

    public HashMap<Shippable,Integer> getShippableItems(){
        //here is my list of shippable items that implement the shippable interface
        HashMap<Shippable,Integer> shippableProducts= new HashMap<>();

        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            if(item.getKey().isShippable()){
                shippableProducts.put(new ShippableProduct(item.getKey()), item.getValue());
            }
        }
        return shippableProducts;
    }

    public void add(Product product) {
        try{
            product.decrementQuantity();
            cartItems.put(product, 1);
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }

    }
    public void add(Product product, Integer quantity){
        try{
            product.decrementQuantity(quantity);
            cartItems.put(product, quantity);
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }
    }

    public void remove(Product product) {
        if (!cartItems.containsKey(product)){
            System.out.println("Product is not in cart.");
            return;
        }
        cartItems.remove(product);
        product.incrementQuantity(1);
    }

    public void remove(Product product,Integer quantity) {
        if (!cartItems.containsKey(product)){
            System.out.println("Product is not in cart.");
            return;
        }
        Integer oldQuantity=cartItems.get(product);
        if((oldQuantity-quantity)<0){
            throw new IllegalStateException("Quantity in cart is less than the quantity you want to remove");
        }
        else if ((oldQuantity-quantity)==0){
            cartItems.remove(product);
        }
        else {
            cartItems.put(product,oldQuantity-quantity);
        }
        product.incrementQuantity(quantity);
    }
    public void clear() {
        cartItems.clear();
        total = 0;
    }
    public boolean isEmpty(){
        return cartItems.isEmpty();
    }

    public void checkExpiryDates() throws IllegalStateException{
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            if(item.getKey().isExpirable()){
                if(item.getKey().isExpired()){
                    throw new IllegalStateException("The item: "+item.getKey().getName()+" has expired.");
                }
            }
        }
    }
    public void displayCartItems(){
        System.out.println("ITEMS IN CART:");

        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            System.out.println(item.getValue()+"X "+item.getKey().getName()+"\t"+item.getKey().getPrice()*item.getValue()+" EGP");
        }
    }

}
