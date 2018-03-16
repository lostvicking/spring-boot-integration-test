package io.cucumber.tutorial;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class HealthCheck implements HealthIndicator {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Override
    public Health health() {
        boolean isRunning = isRunning();
        if (isRunning) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }

    private boolean isRunning() {
        return rabbitTemplate.isRunning();
    }
}
