package com.niit.jdp.userservice.rabbitmqproducer;

import com.niit.jdp.userservice.domain.User;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProducerMapping {

    private RabbitTemplate rabbitTemplate;

    private DirectExchange directExchange;

    public ProducerMapping(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendDTOToQueue(User user) {
        rabbitTemplate.convertAndSend(directExchange.getName(), "user_neo", user);
    }
}
