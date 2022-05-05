package pizzalab.domain;

import lombok.Data;

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
