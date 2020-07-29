package com.xilixili.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRabbitMQConfig {
    @Bean("email")
    Queue emailQueue(){
        return new Queue("emailQueue");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding bindingEmailQueueToDirectExchange(@Autowired @Qualifier("email")Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("sendEmail");
    }
}
