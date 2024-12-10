package com.example.test.controller;

import com.example.test.dto.request.ProduceMessageRequest;
import com.example.test.service.RabbitProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rabbit-mq")
public class RabbitController {
  @Autowired private RabbitProducerService rabbitProducerService;

  @PostMapping("/produce")
  public String produceMessage(@RequestBody ProduceMessageRequest body) throws Exception {
    log.info("Producing message");

    try {
      switch (body.getType()) {
        case "CUSTOM":
          rabbitProducerService.sendToCustomQueue(body.getMessage());
          break;

        case "CUSTOM-BATCH-1":
          rabbitProducerService.sendToCustomBatchQueue1(body.getMessage());
          break;

        case "CUSTOM-BATCH-2":
          rabbitProducerService.sendToCustomBatchQueue2(body.getMessage());
          break;

        default:
          rabbitProducerService.sendToDefaultQueue(body.getMessage());
      }

    } catch (Exception e) {
      throw new Exception("Error while sending message => " + e.getMessage());
    }

    return "Message Produced";
  }
}
