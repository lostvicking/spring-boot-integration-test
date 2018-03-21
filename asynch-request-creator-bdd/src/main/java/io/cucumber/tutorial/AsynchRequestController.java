package io.cucumber.tutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class AsynchRequestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    private static final Logger LOGGER = LoggerFactory.getLogger(AsynchRequestApplication.class);

    @RequestMapping(method = RequestMethod.POST, value = "/create-request")
    @ResponseStatus(HttpStatus.OK)
    public Request createRequest(@RequestBody Request request) {
        LOGGER.info("Received request: " + request);

        this.rabbitTemplate.convertAndSend(queue.getName(), request.getContent());

        return request;
    }


}
