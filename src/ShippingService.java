import java.lang.System;
import java.util.ArrayList;

public class ShippingService {
    private ArrayList<Product> toBeShipped;
    public ShippingService(ArrayList<Product> toBeShipped){
        this.toBeShipped=toBeShipped;
    }
    public void displayShippingDetails(){
        System.out.println("** Shipment notice **");

    }
}
