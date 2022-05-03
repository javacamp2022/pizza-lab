import domain.Customer;

import java.util.ArrayList;
import java.util.List;

public class DeliveryService {

  //TODO move this to repository
  private static final List<Customer> customers = new ArrayList<>();

  public void addCustomer(Customer customer) {
    //TODO Validations?
    customers.add(customer);
  }


}
