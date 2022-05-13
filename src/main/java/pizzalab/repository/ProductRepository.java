package pizzalab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pizzalab.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAll();

  @Query(value = "SELECT p FROM Product p WHERE p.quantity >0 ORDER BY type")
  List<Product> findMenuItems();

  @Modifying
  @Query(value = "INSERT INTO Product (product_type, id, quantity, price, description) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
  void insertProduct(int product_type, int id, int quantity, double price, String description);
}
