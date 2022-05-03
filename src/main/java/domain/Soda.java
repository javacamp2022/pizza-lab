package domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Soda extends Product {

  private String name;

  @Builder
  public Soda(int quantity, long price, String name) {
    this.quantity = quantity;
    this.price = price;
    this.name = name;
  }

  @Override
  public String getDescription() {
    return String.format("Soda %s", name);
  }
}
