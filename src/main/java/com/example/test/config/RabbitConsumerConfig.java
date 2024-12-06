package com.example.test.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class RabbitConsumerConfig {
  ConnectionFactory connectionFactory;
  MessageConverter messageConverter;

  @Autowired
  public RabbitConsumerConfig(
      ConnectionFactory connectionFactory, MessageConverter messageConverter) {
    this.connectionFactory = connectionFactory;
    this.messageConverter = messageConverter;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory defaultListenerContainerFactory() {
    log.info("Creating Default Listener Container Factory");

    SimpleRabbitListenerContainerFactory listenerContainerFactory =
        new SimpleRabbitListenerContainerFactory();

    listenerContainerFactory.setConnectionFactory(connectionFactory);
    listenerContainerFactory.setMessageConverter(messageConverter);
    listenerContainerFactory.setConcurrentConsumers(250);
    listenerContainerFactory.setMaxConcurrentConsumers(500);
    listenerContainerFactory.setPrefetchCount(5000);

    return listenerContainerFactory;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory customListenerContainerFactory() {
    log.info("Creating Custom Listener Container Factory");

    SimpleRabbitListenerContainerFactory listenerContainerFactory =
        new SimpleRabbitListenerContainerFactory();

    listenerContainerFactory.setConnectionFactory(connectionFactory);
    listenerContainerFactory.setMessageConverter(messageConverter);
    listenerContainerFactory.setConcurrentConsumers(5);
    listenerContainerFactory.setMaxConcurrentConsumers(50);
    listenerContainerFactory.setPrefetchCount(1);

    return listenerContainerFactory;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory customBulkListenerContainerFactory() {
    log.info("Creating Bulk Listener Container Factory");

    SimpleRabbitListenerContainerFactory listenerContainerFactory =
        new SimpleRabbitListenerContainerFactory();

    listenerContainerFactory.setConnectionFactory(connectionFactory);
    listenerContainerFactory.setMessageConverter(messageConverter);
    listenerContainerFactory.setConsumerBatchEnabled(true);
    listenerContainerFactory.setBatchSize(20);
    listenerContainerFactory.setConcurrentConsumers(1);
    listenerContainerFactory.setPrefetchCount(100);

    return listenerContainerFactory;
  }
}
