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


    public boolean hasShippables(){
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            if(item.getKey().isShippable()){
                return true;
            }
        }
        return false;
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

    public void checkAllProductsStock() throws IllegalStateException{
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            if(item.getKey().getQuantity()<item.getValue()){
                throw new IllegalStateException("Number of "+item.getKey().getName()+" in cart exceeds number in stock."+"("+item.getKey().getQuantity()+")");
            }
        }
    }

    public void reduceStock() {
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            item.getKey().decreaseStockBy(item.getValue());
        }
    }



    public void add(Product product) {
        try{
            product.checkAvailability();
            cartItems.put(product, 1);
        }catch(IllegalStateException e){
            System.out.println(e.getMessage());
        }

    }
    public void add(Product product, Integer quantity){
        try{
            product.checkAvailability(quantity);
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
    }

    public void remove(Product product,Integer quantity) {
        if (!cartItems.containsKey(product)){
            System.out.println("Product is not in cart.");
            return;
        }
        Integer oldQuantity=cartItems.get(product);
        if((oldQuantity-quantity)<0){
            throw new IllegalStateException("Failed to add "+product.getName()+". Quantity in cart is less than the quantity you want to remove");
        }
        else if ((oldQuantity-quantity)==0){
            cartItems.remove(product);
        }
        else {
            cartItems.put(product,oldQuantity-quantity);
        }
    }
    public void clear() {
        cartItems.clear();
        total = 0;
    }
    public boolean isEmpty(){
        return cartItems.isEmpty();
    }
//    public boolean hasInvalidQuantities(){
//        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
//            if(item.getValue()>item.getKey().getQuantity()){
//                return true;
//            }
//        }
//        return false;
//    }

    public void checkExpiryDates() throws IllegalStateException{
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            if(item.getKey().isExpirable()){
                if(item.getKey().isExpired()){
                    throw new IllegalStateException("The item: "+item.getKey().getName()+" has expired."+" Expiry date: "+item.getKey().getExpiryDate());
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
