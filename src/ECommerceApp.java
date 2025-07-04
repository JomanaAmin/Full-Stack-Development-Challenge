import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ECommerceApp {
    public static void main(String[] args) {
        Customer customer1=new Customer("Jomana",2);
        Customer customer2=new Customer("Hussein",200);


        Product cheese= new Product("Cheese",12.75,2,true,250,true, LocalDate.now().plusDays(14));
        Product milk= new Product("Milk",20,5,true,1000,true, LocalDate.now().minusDays(30));
        Product charger= new Product("Charger",40,1,true,100,false,null);


        customer1.getCart().add(milk,6);
        customer1.checkout();
        customer1.getCart().remove(milk,2);
        customer1.getCart().add(milk,2);
        customer1.getCart().add(charger,3);
        customer1.checkout();
        customer1.getCart().remove(milk,2);
        customer1.getCart().add(charger,1);
        customer1.checkout();

        customer2.getCart().add(cheese,2);
        customer2.checkout();

        customer2.getCart().add(charger);
        customer2.checkout();

        customer1.checkout();


    }
}