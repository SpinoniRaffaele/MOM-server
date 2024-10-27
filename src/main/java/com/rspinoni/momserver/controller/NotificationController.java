package com.rspinoni.momserver.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NotificationController {

  @MessageMapping("/send")
  @SendTo("/topic/notifications")
  public String sendNotification(String message) {
    log.info(message);
    return "Received and forwarded: " + message;
  }
}
