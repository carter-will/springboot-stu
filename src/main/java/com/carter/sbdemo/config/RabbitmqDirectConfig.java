package com.carter.sbdemo.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqDirectConfig {

    @Bean
    public Queue paymentNotifyQueue() {
        return new Queue("notify.payment");
    }

    @Bean
    public Queue queryOrderQueue() {
        return new Queue("query.rpc");
    }

}
