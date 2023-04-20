/*
 * Author Name: Mohini
 * Date: 1/18/2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.userservice.rabbitmqproducer;
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

        public void sendDTOToQueue(UserDTO userDTO) {
            rabbitTemplate.convertAndSend(directExchange.getName(), "user_routing", userDTO);
        }
}
