package com.carter.sbdemo.config;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpACKConfig {

    public static final String EXCHANGE   = "spring-boot-exchange";
    public static final String ROUTINGKEY = "spring-boot-routingKey";

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue("spring-boot-queue", true); //队列持久

    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(defaultExchange()).with(ROUTINGKEY);
    }


}
