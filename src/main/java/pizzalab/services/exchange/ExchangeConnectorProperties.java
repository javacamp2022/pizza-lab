package pizzalab.services.exchange;

import lombok.Data;

import org.springframework.stereotype.Component;

@Data
@Component
public class ExchangeConnectorProperties {

  private String hostUrl = "https://romanian-exchange-rate-bnr-api.herokuapp.com";
}
