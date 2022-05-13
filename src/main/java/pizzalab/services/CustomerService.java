package pizzalab.services;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pizzalab.entity.Customer;
import pizzalab.repository.CustomerRepository;
import pizzalab.repository.ProductRepository;
import pizzalab.exception.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  @Transactional(rollbackFor = SQLException.class)
  public void addCustomer(Customer customer) {
    customerRepository.save(customer);
    productRepository.insertProduct(1, 10, 20, 39, "Product 1");
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
