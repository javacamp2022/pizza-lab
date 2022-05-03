import domain.Menu;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

public class PantryService {

  private static final PantryRepository repository = new PantryRepository();

  public Menu listMenu() {

    return Menu.builder()
        .items(
            repository.getProducts().stream()
                .filter(p -> p.getQuantity() > 0)
                .map(p -> Menu.MenuItem.builder()
                    .price(p.getPrice())
                    .description(p.getDescription())
                    .build())
                .sorted()
                .collect(Collectors.toList()))
        .createdAt(new Date())
        .lastUpdatedAt(new Date())
        .validUntil(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
        .build();
  }
}
