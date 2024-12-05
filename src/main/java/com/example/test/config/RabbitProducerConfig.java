package com.example.test.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitProducerConfig {
  @Bean
  public Queue defaultQueue() {
    return new Queue("default-queue");
  }

  @Bean
  public Queue customQueue() {
    return new Queue("custom-queue");
  }

  @Bean
  public DirectExchange directExchange() {
    return new DirectExchange("direct-exchange");
  }

  @Bean
  public Binding defaultQueueBiding(Queue defaultQueue, DirectExchange directExchange) {
    return BindingBuilder.bind(defaultQueue).to(directExchange).with("default");
  }

  @Bean
  Binding customQueueBiding(Queue customQueue, DirectExchange directExchange) {
    return BindingBuilder.bind(customQueue).to(directExchange).with("custom");
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

    connectionFactory.setHost("localhost");
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setPort(5672);

    return connectionFactory;
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);

    template.setMessageConverter(messageConverter());

    return template;
  }
}
