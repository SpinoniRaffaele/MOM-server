package com.rspinoni.momserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rspinoni.momserver.model.Message;
import com.rspinoni.momserver.model.User;
import com.rspinoni.momserver.model.UserWithPreKey;
import com.rspinoni.momserver.model.exception.InvalidDeviceException;
import com.rspinoni.momserver.service.UserService;
import com.rspinoni.momserver.service.MessageService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authorization")
@Slf4j
public class AuthorizationController {

  private final UserService authorizationService;

  private final MessageService messageService;

  @Autowired
  AuthorizationController(UserService service, MessageService messageService) {
    this.authorizationService = service;
    this.messageService = messageService;
  }

  @PostMapping("/connect")
  @ResponseStatus(HttpStatus.OK)
  public List<Message> connect(@RequestBody User user) {
    if (authorizationService.validateUser(user)) {
      List<Message> newMessages = messageService.retrieveQueuedMessages(user.phoneNumber());
      //todo: you should delete the queued messages (do not do it for now for testing)
      //todo: the client should open the websocket connection after processing the new messages
      log.info("User with device {} connected", user.deviceId());
      return newMessages;
    } else {
      throw new InvalidDeviceException("Invalid user's device provided, register the device first");
    }
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody UserWithPreKey user) {
    //TODO: validate phoneNumber before registering the device
    authorizationService.registerUser(user);
  }
}
