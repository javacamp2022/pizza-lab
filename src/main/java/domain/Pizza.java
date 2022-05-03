package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pizza extends Product {

  private PizzaType type;

  @Builder
  public Pizza(int quantity, long price, PizzaType type) {
    this.quantity = quantity;
    this.price = price;
    this.type = type;
  }

  @Override
  public String getDescription() {
    return String.format("Pizza %s", type);
  }
}
