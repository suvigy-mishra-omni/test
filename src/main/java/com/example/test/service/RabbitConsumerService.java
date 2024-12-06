package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.*;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
      queues = CUSTOM_BULK_QUEUE,
      ackMode = "MANUAL",
      containerFactory = "customBulkListenerContainerFactory")
  public void customQueueBulkListener(List<Message<String>> message, Channel channel)
      throws IOException {
    log.info("Received {} messages", message.size());

    log.info("Messages => {}", message.stream().map(Message::getPayload));

    Long maxTag =
        (Long)
            message.stream()
                .max((Comparator.comparingLong(o -> (Long) o.getHeaders().get("amqp_deliveryTag"))))
                .get()
                .getHeaders()
                .get("amqp_deliveryTag");

    channel.basicAck(maxTag, true);
  }

  public String getMessage(String currentMessage) {

    return String.format(
        "%s%nConsumer Time Stamp : %s | Time : %s",
        currentMessage.replace("`", "\n").replace("\"", ""),
        System.currentTimeMillis(),
        LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
  }
}
