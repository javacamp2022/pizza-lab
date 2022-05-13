package pizzalab.services.exchange;

import pizzalab.domain.exchange.ExchangeResponse;

public interface ExchangeService {
    ExchangeResponse exchangeRonToEuro(Double amount);
}
