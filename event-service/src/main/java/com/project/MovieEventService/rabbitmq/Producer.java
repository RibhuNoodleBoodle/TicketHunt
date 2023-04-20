package com.project.MovieEventService.rabbitmq;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {


    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private DirectExchange directExchange;

    public void sendDtoToQueue(BookingDTO bookingDTO){
        rabbitTemplate.convertAndSend(directExchange.getName(),"booking_routing",bookingDTO);
    }

}
