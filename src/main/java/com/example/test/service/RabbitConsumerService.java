package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.*;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitConsumerService {
  @RabbitListener(
      queues = DEFAULT_QUEUE,
      ackMode = "AUTO",
      containerFactory = "defaultListenerContainerFactory",
      messageConverter = "messageConverter")
  public void defaultQueueListener(Message<String> message, Channel channel) {
    log.info("Default queue received a message => {}", getMessage(message.getPayload()));
  }

  @RabbitListener(
      queues = CUSTOM_QUEUE,
      ackMode = "MANUAL",
      containerFactory = "customListenerContainerFactory",
      messageConverter = "messageConverter")
  public void customQueueListener(Message<String> message, Channel channel) throws IOException {
    log.info("Custom queue received a message => {}", getMessage(message.getPayload()));

    channel.basicAck((Long) message.getHeaders().get("amqp_deliveryTag"), false);
  }

  @RabbitListener(
      queues = CUSTOM_BATCH_QUEUE,
      ackMode = "MANUAL",
      containerFactory = "customBatchListenerContainerFactory")
  public void customQueueBatchListener(List<Message<String>> messages, Channel channel)
      throws IOException {
    log.info("Received {} messages", messages.size());

    for (Message<String> message : messages) {
      channel.basicAck((Long) message.getHeaders().get("amqp_deliveryTag"), false);
    }
  }

  public String getMessage(String currentMessage) {

    return String.format(
        "%s%nConsumer Time Stamp : %s | Time : %s",
        currentMessage.replace("`", "\n").replace("\"", ""),
        System.currentTimeMillis(),
        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
  }
}
