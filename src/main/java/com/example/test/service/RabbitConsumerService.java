package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.CUSTOM_QUEUE;
import static com.example.test.config.RabbitProducerConfig.DEFAULT_QUEUE;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumerService {
  @RabbitListener(queues = DEFAULT_QUEUE  , ackMode = "AUTO")
  public void defaultQueueListener(Object payload) {
    log.info("Default queue received an message => {}", payload.toString());
  }

  @RabbitListener(queues = CUSTOM_QUEUE, ackMode = "MANUAL")
  public void customQueueListener(Object payload) {
    log.info("Custom queue received an message => {}", payload.toString());
  }
}
