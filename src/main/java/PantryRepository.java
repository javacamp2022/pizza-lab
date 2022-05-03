import lombok.Getter;

import domain.Pizza;
import domain.PizzaType;
import domain.Product;
import domain.Soda;

import java.util.ArrayList;
import java.util.List;

@Getter
//TODO make this singleton
public class PantryRepository {

  private List<Product> products = new ArrayList<>();

  public PantryRepository() {
    Pizza qsPizza = Pizza.builder()
        .type(PizzaType.QUATTRO_STAGIONI)
        .price(10)
        .quantity(25)
        .build();
    Pizza capricciosaPizza = Pizza.builder()
        .type(PizzaType.CAPRICCIOSA)
        .price(12)
        .quantity(10)
        .build();
    Soda cola = Soda.builder()
        .name("Cola")
        .price(2)
        .quantity(50)
        .build();

    products.add(qsPizza);
    products.add(capricciosaPizza);
    products.add(cola);
  }


}
