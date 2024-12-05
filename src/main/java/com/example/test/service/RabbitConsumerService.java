package com.example.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumerService {
  @RabbitListener(queues = "default-queue", ackMode = "AUTO")
  public void defaultQueueListener(Object payload) {
    log.info("Default queue received an message => {}", payload.toString());
  }

  @RabbitListener(queues = "custom-queue", ackMode = "MANUAL")
  public void customQueueListener(Object payload) {
    log.info("Custom queue received an message => {}", payload.toString());
  }
}
