import static java.util.Arrays.asList;

import domain.Address;
import domain.Customer;
import domain.Menu;

public class Main {

  private static final DeliveryService deliveryService = new DeliveryService();
  private static final PantryService pantryService = new PantryService();


  public static void main(String[] args) {

    System.out.println("Welcome to Pizza Delivery Service");

    Customer john = Customer.builder()
        .name("John")
        .phone("012345678")
        .addresses(asList(Address.builder().street("John's street").build()))
        .build();
    deliveryService.addCustomer(john);

    Menu menu = pantryService.listMenu();

    System.out.println(menu);

  }
}
