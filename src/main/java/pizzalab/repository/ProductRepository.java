package pizzalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pizzalab.domain.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAll();

  @Query(value = "SELECT p FROM Product p WHERE p.quantity >0 ORDER BY type")
  List<Product> findMenuItems();
}
