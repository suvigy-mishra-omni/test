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
  public SimpleRabbitListenerContainerFactory listenerContainerFactory() {
    log.info("Creating Listener Container Factory");

    SimpleRabbitListenerContainerFactory listenerContainerFactory =
        new SimpleRabbitListenerContainerFactory();

    listenerContainerFactory.setConnectionFactory(connectionFactory);
    listenerContainerFactory.setMessageConverter(messageConverter);
    listenerContainerFactory.setMaxConcurrentConsumers(50);
    listenerContainerFactory.setPrefetchCount(1000);

    return listenerContainerFactory;
  }
}
