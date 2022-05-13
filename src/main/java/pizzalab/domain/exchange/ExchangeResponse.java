package pizzalab.domain.exchange;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExchangeResponse {
    private final Double amount;
    private final Double curs;
}
