package com.example.test.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    SimpleRabbitListenerContainerFactory listenerContainerFactory =
        new SimpleRabbitListenerContainerFactory();

    listenerContainerFactory.setConnectionFactory(connectionFactory);
    listenerContainerFactory.setMessageConverter(messageConverter);
    listenerContainerFactory.setMaxConcurrentConsumers(50);
    listenerContainerFactory.setPrefetchCount(1000);

    return listenerContainerFactory;
  }
}
