package com.alwyn.techie.stockmanagement.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue productQueue(){
        return new Queue("productQueue", false);
    }
}
