package pizzalab;

import static java.util.Arrays.asList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pizzalab.domain.Address;
import pizzalab.domain.Customer;
import pizzalab.domain.Menu;

@SpringBootApplication
public class Main implements CommandLineRunner {

  @Autowired
  private DeliveryService deliveryService;
  @Autowired
  public PantryService pantryService;


  public static void main(String[] args) {
    SpringApplication.run(Main.class, args);
  }


  @Override
  public void run(String... args) throws Exception {
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

@RestController
class FirstController {
  @GetMapping("/")
  public String getSomething() {
    return "Hello world!";
  }
}

