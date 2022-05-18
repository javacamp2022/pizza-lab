package pizzalab.services;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pizzalab.entity.Customer;
import pizzalab.repository.CustomerRepository;
import pizzalab.repository.ProductRepository;
import pizzalab.exception.CustomerNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final ProductRepository productRepository;

  public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
    this.customerRepository = customerRepository;
    this.productRepository = productRepository;
  }

  @Transactional(rollbackFor = SQLException.class)
  public Customer addCustomer(Customer customer) {
    if (customerRepository.findFirstByName(customer.getName()) != null) {
      return null;
    }

    Customer saved = customerRepository.save(customer);
    return saved;
//    productRepository.insertProduct(1, 10, 20, 39, "Product 1");
  }

  public List<Customer> findAll() {
    return customerRepository.findAll();
  }

  public Customer findById(Long customerId) {

    Optional<Customer> customerOptional = customerRepository.findById(customerId);
    if (!customerOptional.isPresent()) {
      throw new CustomerNotFoundException("Customer not found");
    }
    return customerOptional.get();
  }
}
