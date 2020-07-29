package com.xilixili.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class CommentRabbitMQConfig {
    @Bean("comment")
    Queue emailQueue(){
        return new Queue("commentQueue");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange("direct-exchange");
    }

    @Bean
    Binding bindingEmailQueueToDirectExchange(@Autowired @Qualifier("comment")Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("comment");
    }
}
