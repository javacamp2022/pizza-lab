package pizzalab.services.exchange;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pizzalab.domain.exchange.ExchangeResponse;

@Service
@Qualifier("cache")
public class CachedExchangeService implements ExchangeService {

    private final ExchangeService exchangeService;
    private final Cache<String, Double> cache;

    public CachedExchangeService(@Qualifier("api") ExchangeService exchangeService,
                                 Cache<String, Double> cache) {
        this.exchangeService = exchangeService;
        this.cache = cache;
    }

    @Override
    public ExchangeResponse exchangeRonToEuro(Double amount) {
        Double curs = cache.get("CURS", (key) -> exchangeService.exchangeRonToEuro(amount).getCurs());
        return new ExchangeResponse(amount / curs, curs);
    }
}
