public class ShippableProduct implements Shippable{
    private Product product;
    public ShippableProduct(Product product){
        this.product=product;
    }
    @Override
    public String getName(){
        return this.product.getName();
    }
    public double getWeight(){
        return this.product.getWeight();
    }
}
