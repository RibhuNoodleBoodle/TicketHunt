package com.project.MovieEventService.rabbitmq;


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

    private String exchange_name="booking_exchange";
    private String register_queue="booking_queue";
    private String register_neo = "booking_neo";

    @Bean
    public DirectExchange getDirectExchange(){

        return new DirectExchange(exchange_name);
    }

    @Bean
    public Queue getQueue(){
        return new Queue(register_queue, true);
    }

    @Bean
    public Queue getNeoQueue(){return new Queue(register_neo, true);}

    @Bean
    public Jackson2JsonMessageConverter getProducerJacksonConvertor(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Binding getBinding(){

        return BindingBuilder.bind(getQueue()).to(getDirectExchange()).with("booking_routing");
    }

    @Bean
    public Binding getNeoBinding(DirectExchange directExchange){
        return BindingBuilder.bind(getNeoQueue()).to(getDirectExchange()).with("booking_neo");
    }

    @Bean
    public RabbitTemplate getRabbitTemplate(final ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(getProducerJacksonConvertor());
        return rabbitTemplate;
    }

}
