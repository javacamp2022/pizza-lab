package pizzalab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizzalab.domain.Menu;
import pizzalab.domain.MenuItem;
import pizzalab.repository.ProductRepository;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class PantryService {

  @Autowired
  private ProductRepository repository;

  public Menu listMenu() {

    return Menu.builder()
        .items(
            repository.findAll().stream()
                .filter(p -> p.getQuantity() > 0)
                .map(p -> MenuItem.builder()
                    .price(p.getPrice())
                    .description(p.getDescription())
                    .build())
//                .sorted()
                .collect(Collectors.toList()))
        .createdAt(new Date())
        .lastUpdatedAt(new Date())
        .validUntil(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
        .build();
  }
}
