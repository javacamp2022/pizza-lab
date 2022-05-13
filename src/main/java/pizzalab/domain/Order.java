package pizzalab.domain;

import lombok.Data;
import pizzalab.entity.Address;
import pizzalab.entity.Customer;
import pizzalab.entity.Product;

import java.util.Date;
import java.util.List;

@Data
public class Order {

  private Customer customer;
  private Address address;
  private Date createdAt;
  private Date expectedTime;
  private Date actualDeliveryTime;

  private Long total;
  private List<Product> items;
}
