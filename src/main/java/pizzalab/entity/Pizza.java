package pizzalab.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pizzalab.domain.PizzaType;
import pizzalab.entity.Product;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
@NoArgsConstructor
@DiscriminatorValue("1")
public class Pizza extends Product {

  private PizzaType type;

  @Builder
  public Pizza(int quantity, double price, PizzaType type) {
    this.quantity = quantity;
    this.price = price;
    this.type = type;
  }

}
