package pizzalab;

import org.springframework.stereotype.Service;

import pizzalab.domain.Customer;
import pizzalab.rest.exception.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

  //TODO move this to repository
  private static final List<Customer> customers = new ArrayList<>();

  public void addCustomer(Customer customer) {
    //TODO Validations?
    customers.add(customer);
  }


  public List<Customer> findAll() {
    return customers;
  }

  public Customer findById(String customerId) {
    return customers.stream()
        .filter(customer -> customer.getName().equals(customerId))
        .findFirst()
        .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
  }
}
