package bg.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import java.util.Map;
//{
//  "disclaimer": "Usage subject to terms: https://openexchangerates.org/terms",
//  "license": "https://openexchangerates.org/license",
//  "timestamp": 1698318000,
//  "base": "USD",
//  "rates": {
//    "BGN": 1.854,
//    "EUR": 0.948994
//  }
//}

public record ExchangeRatesDTO(String base, Map<String, BigDecimal> rates) {
}
