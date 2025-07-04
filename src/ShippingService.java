import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShippingService {
    private HashMap<Shippable,Integer> toBeShipped;
    private double totalWeight;
    private double shippingFee;
    public ShippingService(){
        this.toBeShipped=new HashMap<Shippable,Integer> ();
    }

    public ShippingService(HashMap<Shippable,Integer> toBeShipped){
        this.toBeShipped=toBeShipped;
    }
    public void displayShippingDetails(){

        System.out.println("** Shipment notice **");
        for(Map.Entry<Shippable,Integer> shippableItem: toBeShipped.entrySet()){
            System.out.println(shippableItem.getValue()+"X "+shippableItem.getKey().getName()+"\t"+shippableItem.getKey().getWeight()*shippableItem.getValue()+"g");
        }
        System.out.println("Total package weight: "+ this.getTotalWeight()/1000+"kg");
    }

    public double getTotalWeight(){
        this.totalWeight=0;

        for(Map.Entry<Shippable,Integer> shippableItem: toBeShipped.entrySet()){
            this.totalWeight+=shippableItem.getKey().getWeight()*shippableItem.getValue();
        }
        return this.totalWeight;
    }

    public double getShippingFee(){
        this.shippingFee=this.getTotalWeight()*0.005;
        return this.shippingFee;
    }

}
