/*
 * Author Name: Mohini
 * Date: 1/18/2023
 * Created With: IntelliJ IDEA Community Edition
 */
package com.niit.jdp.userservice.rabbitmqproducer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    // Creating exchange , queue, jackson, binding, rabbit template

    @Bean
    public DirectExchange getDirectExchange() {
        String exchange_name = "user_exchange";
        return new DirectExchange(exchange_name);
    }
    @Bean Queue secondQueue(){
        String queue2 = "user_neo_queue";
        return new Queue(queue2);
    }
    @Bean
    public Queue firstQueue() {
        String queue1 = "user_queue";
        return new Queue(queue1);
    }
    @Bean
    public Jackson2JsonMessageConverter getProducerJacksonConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding getBinding() {
        return BindingBuilder.bind(firstQueue()).to(getDirectExchange()).with("user_routing");
    }

    @Bean
    public Binding getSecondBinding() {
        return BindingBuilder.bind(secondQueue()).to(getDirectExchange()).with("user_neo");
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getProducerJacksonConverter());

        return rabbitTemplate;
    }
}
