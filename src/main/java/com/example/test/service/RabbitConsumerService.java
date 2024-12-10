package com.example.test.service;

import static com.example.test.config.RabbitProducerConfig.*;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
      containerFactory = "customBatchListenerContainerFactory",
      messageConverter = "messageConverter")
  public void customQueueListener(List<Message<String>> messages, Channel channel)
      throws IOException {
    log.info("Received {} messages in custom queue", messages.size());

    log.info(
        "Batched messages fir custom queue => {}",
        Arrays.toString(
            messages.stream().map(message -> getMessage(message.getPayload())).toArray()));

    String result = new String[] {"SUCCESS", "ERROR"}[new Random().nextInt(0, 2)];

    log.info(
        "Batched messages for custom queue => \n{}",
        Arrays.toString(
            messages.stream().map(message -> getMessage(message.getPayload())).toArray()));

    for (Message<String> message : messages) {
      if (result.equals("SUCCESS")) {
        channel.basicAck((Long) message.getHeaders().get("amqp_deliveryTag"), false);
      } else {
        channel.basicNack((Long) message.getHeaders().get("amqp_deliveryTag"), false, true);
      }
    }
  }

  @RabbitListener(
      queues = {CUSTOM_BATCH_QUEUE_1, CUSTOM_BATCH_QUEUE_2},
      ackMode = "MANUAL",
      containerFactory = "customBatchListenerContainerFactory")
  public void customQueueBatchListener(List<Message<String>> messages, Channel channel)
      throws IOException {
    log.info("Received {} messages", messages.size());

    log.info(
        "Batched messages for custom queues 1 and 2 => {}",
        Arrays.toString(
            messages.stream().map(message -> getMessage(message.getPayload())).toArray()));

    String result = new String[] {"SUCCESS", "ERROR"}[new Random().nextInt(0, 2)];

    for (Message<String> message : messages) {
      if (result.equals("SUCCESS")) {
        channel.basicAck((Long) message.getHeaders().get("amqp_deliveryTag"), false);
      } else {
        channel.basicNack((Long) message.getHeaders().get("amqp_deliveryTag"), false, true);
      }
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
