package bg.softuni.mobilele.init;

import bg.softuni.mobilele.config.OpenExchangeRatesConfig;
import bg.softuni.mobilele.model.dto.ExchangeRatesDTO;
import bg.softuni.mobilele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RatesInit implements CommandLineRunner {
    private final OpenExchangeRatesConfig openExchangeRatesConfig;
    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public RatesInit(OpenExchangeRatesConfig openExchangeRatesConfig,
                     RestTemplate restTemplate,
                     CurrencyService currencyService) {
        this.openExchangeRatesConfig = openExchangeRatesConfig;
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (openExchangeRatesConfig.isEnabled()) {
            String openExchangeRateUrlTemplate = new StringBuilder()
                    .append(openExchangeRatesConfig.getSchema())
                    .append("://")
                    .append(openExchangeRatesConfig.getHost())
                    .append(openExchangeRatesConfig.getPath())
                    .append("?app_id={app_id}&symbols={symbols}")
                    .toString();

            Map<String, String> requestParams = Map.of(
                    "app_id", openExchangeRatesConfig.getAppId(),
                    "symbols", openExchangeRatesConfig.getSymbols().stream().collect(Collectors.joining(","))
            );

            ExchangeRatesDTO exchangeRatesDTO = restTemplate
                    .getForObject(openExchangeRateUrlTemplate,
                            ExchangeRatesDTO.class,
                            requestParams);

            currencyService.refreshRates(exchangeRatesDTO);
        }
    }
}
