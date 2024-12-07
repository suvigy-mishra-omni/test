package com.example.test.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitProducerConfig {
  public static final String DEFAULT_QUEUE = "default-queue";
  public static final String CUSTOM_QUEUE = "custom-queue";
  public static final String CUSTOM_BATCH_QUEUE = "custom-batch-queue";
  public static final String DIRECT_EXCHANGE = "direct-exchange";
  public static final String DEFAULT_ROUTING_KEY = "default";
  public static final String CUSTOM_ROUTING_KEY = "custom";
  public static final String CUSTOM_BATCH_ROUTING_KEY = "custom-batch";

  @Bean
  public Queue defaultQueue() {
    log.info("Creating Default Queue");

    return new Queue(DEFAULT_QUEUE);
  }

  @Bean
  public Queue customQueue() {
    log.info("Creating Custom Queue");

    return new Queue(CUSTOM_QUEUE);
  }

  @Bean
  public Queue customBatchQueue() {
    log.info("Creating Custom Batch Queue");

    return new Queue(CUSTOM_BATCH_QUEUE);
  }

  @Bean
  public DirectExchange directExchange() {
    log.info("Creating Direct Exchange");

    return new DirectExchange(DIRECT_EXCHANGE);
  }

  @Bean
  public Binding defaultQueueBiding(Queue defaultQueue, DirectExchange directExchange) {
    log.info("Creating Default Queue Biding");

    return BindingBuilder.bind(defaultQueue).to(directExchange).with(DEFAULT_ROUTING_KEY);
  }

  @Bean
  Binding customQueueBiding(Queue customQueue, DirectExchange directExchange) {
    log.info("Creating Custom Queue Binding");

    return BindingBuilder.bind(customQueue).to(directExchange).with(CUSTOM_ROUTING_KEY);
  }

  @Bean
  Binding customBatchQueueBiding(Queue customBatchQueue, DirectExchange directExchange) {
    log.info("Creating Custom Batch Queue Binding");

    return BindingBuilder.bind(customBatchQueue).to(directExchange).with(CUSTOM_BATCH_ROUTING_KEY);
  }

  @Bean
  public ConnectionFactory connectionFactory() {
    log.info("Creating Connection Factory for Rabbit");

    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

    connectionFactory.setHost("localhost");
    connectionFactory.setUsername("guest");
    connectionFactory.setPassword("guest");
    connectionFactory.setPort(5672);

    return connectionFactory;
  }

  @Bean
  public MessageConverter messageConverter() {
    ObjectMapper objectMapper = new ObjectMapper();

    return new Jackson2JsonMessageConverter(objectMapper);
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
    log.info("Creating Template");

    RabbitTemplate template = new RabbitTemplate(connectionFactory);

    template.setMessageConverter(messageConverter());

    return template;
  }
}
