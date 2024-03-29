package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.service.MonitoringService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MonitoringServiceImpl implements MonitoringService {
    private Logger LOGGER = LoggerFactory.getLogger(MonitoringServiceImpl.class);
    private Counter offerSearches;

    public MonitoringServiceImpl(MeterRegistry meterRegistry) {
        this.offerSearches = Counter
                .builder("offer_search_cnt")
                .description("How many offer searched we have performed")
                .register(meterRegistry);
    }

    @Override
    public void logOfferSearch() {
        LOGGER.info("Offer serch was performed.");
        offerSearches.increment();
    }
}
