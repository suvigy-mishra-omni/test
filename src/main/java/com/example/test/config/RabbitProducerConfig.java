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
  public static final String CUSTOM_BATCH_QUEUE_1 = "custom-batch-queue-1";
  public static final String CUSTOM_BATCH_QUEUE_2 = "custom-batch-queue-2";
  public static final String DIRECT_EXCHANGE = "direct-exchange";
  public static final String DEFAULT_ROUTING_KEY = "default";
  public static final String CUSTOM_ROUTING_KEY = "custom";
  public static final String CUSTOM_BATCH_ROUTING_KEY_1 = "custom-batch-1";
  public static final String CUSTOM_BATCH_ROUTING_KEY_2 = "custom-batch-2";

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
  public Queue customBatchQueue1() {
    log.info("Creating Custom Batch Queue 1");

    return new Queue(CUSTOM_BATCH_QUEUE_1);
  }

  @Bean
  public Queue customBatchQueue2() {
    log.info("Creating Custom Batch Queue");

    return new Queue(CUSTOM_BATCH_QUEUE_2);
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
  Binding customBatchQueueBiding1(Queue customBatchQueue1, DirectExchange directExchange) {
    log.info("Creating Custom Batch Queue 1 Binding");

    return BindingBuilder.bind(customBatchQueue1).to(directExchange).with(CUSTOM_BATCH_ROUTING_KEY_1);
  }

  @Bean
  Binding customBatchQueueBiding2(Queue customBatchQueue2, DirectExchange directExchange) {
    log.info("Creating Custom Batch Queue 2 Binding");

    return BindingBuilder.bind(customBatchQueue2).to(directExchange).with(CUSTOM_BATCH_ROUTING_KEY_2);
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
