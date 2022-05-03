import domain.Pizza;
import domain.PizzaType;
import domain.Product;
import domain.Soda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// TODO: make this singleton
/*
constructor privat
o instanta
 */
public class PantryRepository {

    private List<Product> availablePizzas = Arrays.asList(Pizza.builder()
            .quantity(10)
            .price(15L)
            .type(PizzaType.CAPRICIOSA)
            .build(),
            Pizza.builder()
                    .quantity(20)
                    .price(12L)
                    .type(PizzaType.MARGARITA)
                    .build(),
            Soda.builder()
                    .quantity(10)
                    .price(5L)
                    .name("Cola")
                    .build());

    public List<Pizza> listAvailablePizzas() {
        return availablePizzas.stream()
                .filter(product -> product instanceof Pizza)
                .map(product -> (Pizza) product)
                .collect(Collectors.toList());
    }
}
