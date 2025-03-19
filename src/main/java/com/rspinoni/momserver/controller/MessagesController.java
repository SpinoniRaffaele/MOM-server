package com.rspinoni.momserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rspinoni.momserver.model.User;
import com.rspinoni.momserver.model.exception.InvalidDeviceException;
import com.rspinoni.momserver.service.MessageService;
import com.rspinoni.momserver.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/messages")
@Slf4j
public class MessagesController {
  private final UserService authorizationService;

  private final MessageService messageService;

  @Autowired
  MessagesController(UserService service, MessageService messageService) {
    this.authorizationService = service;
    this.messageService = messageService;
  }

  @DeleteMapping("/{senderPhoneNumber}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteMessagesFromSender(@PathVariable String senderPhoneNumber, @RequestParam String deviceId,
      @RequestParam String phoneNumber) {
    if (authorizationService.validateUser(new User(deviceId, phoneNumber))) {
      messageService.deleteMessagesFromSender(senderPhoneNumber);
    } else {
      throw new InvalidDeviceException("Invalid user's device provided");
    }
  }
}
