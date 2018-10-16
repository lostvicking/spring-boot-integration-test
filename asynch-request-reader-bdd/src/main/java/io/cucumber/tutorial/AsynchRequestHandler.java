package io.cucumber.tutorial;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by vic on 2018/01/14.
 */
@SpringBootApplication
@RabbitListener(queues = "spring-boot")
public class AsynchRequestHandler {

    @Autowired
    private RequestRepository requestRepository;

    @RabbitHandler
    public void receive(String in) {
        System.out.println(" [x] Received '" + in + "'");
        Request request = new Request();
        request.setContent(in);
        requestRepository.save(request);
    }

    public static void main(String[] args) {
        SpringApplication.run(AsynchRequestHandler.class, args);
    }
}
