package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.*;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducerService {
  @Autowired AmqpTemplate template;

  public void sendToDefaultQueue(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, DEFAULT_ROUTING_KEY, message);
  }

  public void sendToCustomQueue(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, CUSTOM_ROUTING_KEY, message);
  }
}
