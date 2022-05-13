package pizzalab.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pizzalab.entity.Menu;
import pizzalab.entity.MenuItem;
import pizzalab.repository.ProductRepository;
import pizzalab.services.exchange.ExchangeService;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class PantryService {

    private final ProductRepository repository;
    private final ExchangeService exchangeService;

    public PantryService(ProductRepository repository, @Qualifier("cache") ExchangeService exchangeService) {
        this.repository = repository;
        this.exchangeService = exchangeService;
    }

    public Menu listMenu() {
        return Menu.builder()
                .items(
                        repository.findAll().stream()
                                .filter(p -> p.getQuantity() > 0)
                                .map(p -> MenuItem.builder()
                                        .price(exchangeService.exchangeRonToEuro(p.getPrice()).getAmount())
                                        .description(p.getDescription())
                                        .id(p.getId())
                                        .build())
//                .sorted()
                                .collect(Collectors.toList()))
                .createdAt(new Date())
                .lastUpdatedAt(new Date())
                .validUntil(Date.from(Instant.now().plus(2, ChronoUnit.DAYS)))
                .build();
    }
}
