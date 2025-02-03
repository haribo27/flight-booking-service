package ru.zubcov.flightbookingservice.userservice.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class UserMetricsService {

    private final Counter userRequestsCounter;

    public UserMetricsService(MeterRegistry registry) {
        this.userRequestsCounter = registry.counter("user_requests_total");
    }

    public void IncrementUserRequestsCounter() {
        userRequestsCounter.increment();
    }
}
