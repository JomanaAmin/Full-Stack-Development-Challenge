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
        this(null,0);
    }

    public double getTotal(){
        this.total=0;
        for(Map.Entry<Product,Integer> item: cartItems.entrySet()){
            total+=item.getKey().getPrice()*item.getValue();
        }
        return this.total;
    }
}
