package pizzalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzalab.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findFirstByName(String name);
}
