package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitProducerService {
  @Autowired AmqpTemplate template;

  public void sendToDefaultQueue(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, DEFAULT_ROUTING_KEY, customPayload(message));
  }

  public void sendToCustomQueue(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, CUSTOM_ROUTING_KEY, customPayload(message));
  }

  public void sendToCustomBatchQueue1(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, CUSTOM_BATCH_ROUTING_KEY_1, customPayload(message));
  }

  public void sendToCustomBatchQueue2(String message) {
    template.convertAndSend(DIRECT_EXCHANGE, CUSTOM_BATCH_ROUTING_KEY_2, customPayload(message));
  }

  public String customPayload(String message) {
    return String.format(
        "`%s `Producer Time Stamp : %s | Time : %s",
        message,
        System.currentTimeMillis(),
        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
  }
}
