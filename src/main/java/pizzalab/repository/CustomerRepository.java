package pizzalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pizzalab.domain.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  Customer findFirstByName(String name);
}
