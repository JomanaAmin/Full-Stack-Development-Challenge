import java.time.LocalDate;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ECommerceApp {
    public static void main(String[] args) {
        Customer customer1=new Customer("Jomana",140);
        Customer customer2=new Customer("Hussein",1000);


        Product cheese= new Product("Cheese",200,3,true,250,true, LocalDate.now().plusDays(14));
        Product milk= new Product("Milk",20,5,true,1000,true, LocalDate.now().minusDays(30));
        Product charger= new Product("Charger",40,1,true,100,false,null);
        Product videoGame= new Product("Game: The Last of Us",15,6,false,0,false, null);



        customer1.getCart().add(milk,2);//now only milk got added, so if we checkout, it will cause an error cause milk has expired
        customer1.getCart().add(charger,3);//here i added more chargers than in stock(1), so it will cause an error when adding
        customer1.checkout(); //will raise the expiry date exceeded error for milk
        customer1.getCart().remove(milk,2);//removed milk cause it is expired
        customer1.getCart().add(cheese); //we only added 1, and it is not expired
        customer1.checkout(); // error cause of insufficient funds cause cheese costs 200 + shipping fees, meanwhile the balance is 140
        customer1.getCart().remove(cheese);//removed cheese cause it is too expensive
        customer1.getCart().add(videoGame,1);//bought a game instead!
        customer1.checkout(); // only case where customer buys, but not shippable items, so no shipping notice.

        customer2.checkout();//cart cant be empty

        customer1.getCart().add(charger); //only 1 in stock
        customer2.getCart().add(charger); //now charger is both in customer 1 and 2 carts
        customer1.checkout(); //customer 1 buys it, now customer 2 cant
        customer2.checkout(); //as expected, it is sold out

        customer2.getCart().remove(charger); // now we removed it from cart cause it is sold out (the charger)
        customer2.getCart().add(cheese,3);
        customer2.getCart().add(videoGame);
        customer2.checkout();


    }
}