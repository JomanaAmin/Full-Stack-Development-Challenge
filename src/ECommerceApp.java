import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ECommerceApp {
    public static void main(String[] args) {
        Customer customer1=new Customer("Jomana",299.99);
        Product cheese= new Product("Cheese",12.75,2,true,250,true, LocalDate.now().plusDays(14));
        Product milk= new Product("Milk",20,5,true,1000,true, LocalDate.now().minusDays(30));
        Product Charger= new Product("Charger",40,1,true,100,false,null);
        customer1.getCart().add(milk);
        customer1.checkout();
    }
}