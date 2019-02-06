package io.cucumber.tutorial;


import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AsynchRequestApplication {

    private String queueName = "spring-boot";

    @Bean
    Queue queue() {
        // Durable: queue will survive a broker restart
        return new Queue(queueName, true);
    }


    public static void main(String[] args) {
        Application.run(AsynchRequestApplication.class, args);
    }
}
