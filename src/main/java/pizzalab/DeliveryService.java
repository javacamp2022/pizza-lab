package pizzalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzalab.domain.Customer;
import pizzalab.repository.CustomerRepository;
import pizzalab.rest.exception.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

  @Autowired
  private CustomerRepository customerRepository;

  public void addCustomer(Customer customer) {
    customerRepository.save(customer);
  }


  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer findById(String customerName) {

    Customer customer = customerRepository.findFirstByName(customerName);
    if (customer == null) {
      throw new CustomerNotFoundException("Customer not found");
    }
    return customer;
  }
}
