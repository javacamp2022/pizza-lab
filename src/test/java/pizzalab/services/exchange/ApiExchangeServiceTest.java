package pizzalab.services.exchange;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathMatching;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.apache.http.HttpStatus.SC_GATEWAY_TIMEOUT;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pizzalab.domain.exchange.ExchangeResponse;

public class ApiExchangeServiceTest {

  private static WireMockServer wireMockServer;
  private static String hostUrl;

  private ApiExchangeService underTest;

  @BeforeClass
  public static void initialize() {
    wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
    wireMockServer.start();
    int port = wireMockServer.port();
    WireMock.configureFor("localhost", port);
    hostUrl = "http://localhost:" + port;
  }

  @AfterClass
  public static void destroy() {
    verify(2, getRequestedFor(urlEqualTo("/api/latest?access_key=f7dbe1842278-43779b")));
    wireMockServer.stop();
  }

  @Before
  public void setup() {
    ExchangeConnectorProperties properties = new ExchangeConnectorProperties();
    properties.setHostUrl(hostUrl);
    underTest = new ApiExchangeService(properties);
  }

  @Test
  public void test_exchangeRonToEuro_success() {
    // given
    stubFor(
        get(urlPathMatching("/api/latest"))
        .withQueryParam("access_key", equalTo("f7dbe1842278-43779b"))
        .willReturn(aResponse()
                        .withStatus(SC_OK)
                        .withBody("{\n"
                                  + "    \"rates\": {\n"
                                  + "        \"AED\": 1.2863,\n"
                                  + "        \"AUD\": 3.3196,\n"
                                  + "        \"BGN\": 2.5297,\n"
                                  + "        \"BRL\": 0.9335,\n"
                                  + "        \"CAD\": 3.6853,\n"
                                  + "        \"CHF\": 4.738,\n"
                                  + "        \"CNY\": 0.6999,\n"
                                  + "        \"CZK\": 0.2002,\n"
                                  + "        \"DKK\": 0.6649,\n"
                                  + "        \"EGP\": 0.2586,\n"
                                  + "        \"EUR\": 4.9477,\n"
                                  + "        \"GBP\": 5.881,\n"
                                  + "        \"HRK\": 0.6578,\n"
                                  + "        \"HUF\": 1.2761,\n"
                                  + "        \"INR\": 0.0609,\n"
                                  + "        \"JPY\": 3.6529,\n"
                                  + "        \"KRW\": 0.3714,\n"
                                  + "        \"MDL\": 0.2493,\n"
                                  + "        \"MXN\": 0.2362,\n"
                                  + "        \"NOK\": 0.4874,\n"
                                  + "        \"NZD\": 3.0037,\n"
                                  + "        \"PLN\": 1.0627,\n"
                                  + "        \"RSD\": 0.0421,\n"
                                  + "        \"RUB\": 0.0733,\n"
                                  + "        \"SEK\": 0.4745,\n"
                                  + "        \"THB\": 0.1368,\n"
                                  + "        \"TRY\": 0.2976,\n"
                                  + "        \"UAH\": 0.1599,\n"
                                  + "        \"USD\": 4.7247,\n"
                                  + "        \"XAU\": 277.4951,\n"
                                  + "        \"XDR\": 6.3176,\n"
                                  + "        \"ZAR\": 0.2938\n"
                                  + "    },\n"
                                  + "    \"base\": \"RON\",\n"
                                  + "    \"date\": \"2022-05-17T00:00:00.000Z\"\n"
                                  + "}")));

    // when
    ExchangeResponse result = underTest.exchangeRonToEuro(100D);

    // then
    assertNotNull("result should not be null", result);
//    verify(1, getRequestedFor(urlEqualTo("/api/latest?access_key=f7dbe1842278-43779b")));
  }

  @Test
  public void test_exchangeRonToEuro_fail() {
    stubFor(
        get(urlPathMatching("/api/latest"))
            .withQueryParam("access_key", equalTo("f7dbe1842278-43779b"))
            .willReturn(aResponse()
                            .withStatus(SC_GATEWAY_TIMEOUT)));

    ExchangeResponse result = underTest.exchangeRonToEuro(100D);

    assertNotNull("result should not be null", result);
//    verify(1, getRequestedFor(urlEqualTo("/api/latest?access_key=f7dbe1842278-43779b")));

    assertEquals(100D, result.getAmount(), 0.000001);
    assertEquals(4.9, result.getCurs(), 0.000001);
  }
}
