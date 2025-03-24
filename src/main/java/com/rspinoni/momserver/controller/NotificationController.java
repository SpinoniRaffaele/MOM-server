package com.rspinoni.momserver.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NotificationController {

  @MessageMapping("/send/{destinationPhoneNumber}")
  @SendTo("/topic/notifications/{destinationPhoneNumber}")
  public String sendPrivateMessage(String message, @DestinationVariable String destinationPhoneNumber) {
    log.info("Sending: '" + message + "' to " + destinationPhoneNumber);
    return message;
  }
}
