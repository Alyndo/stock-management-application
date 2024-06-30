package com.alwyn.techie.stockmanagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @RabbitListener(queues = "productQueue")
    public void receive(String message){
        logger.info("Received message from RabbitMQ: {}", message);
        //Handle the message
    }
}
