package com.example.test.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducerService {
  @Autowired AmqpTemplate template;

  public void sendToDefaultQueue(String message) {
    template.convertAndSend("default-exchange", "default", message);
  }

  public void sendToCustomQueue(String message) {
    template.convertAndSend("default-exchange", "custom", message);
  }
}
