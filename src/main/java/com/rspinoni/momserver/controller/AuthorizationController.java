package com.rspinoni.momserver.controller;

import com.rspinoni.momserver.model.UserWithPreKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rspinoni.momserver.model.User;
import com.rspinoni.momserver.model.exception.InvalidDeviceException;
import com.rspinoni.momserver.service.AuthorizationService;
import lombok.extern.slf4j.Slf4j;

@RestController("/authorization")
@Slf4j
public class AuthorizationController {

  private final AuthorizationService service;

  @Autowired
  AuthorizationController(AuthorizationService service) {
    this.service = service;
  }

  @PostMapping("/connect")
  @ResponseStatus(HttpStatus.OK)
  public void connect(@RequestBody User user) {
    if (service.validateUser(user)) {
      //todo: initialize websocket connection
      log.info("User with device {} connected", user.deviceId());
    } else {
      throw new InvalidDeviceException("Invalid user's device provided, register the device first");
    }
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void register(@RequestBody UserWithPreKey user) {
    //TODO: validate phoneNumber before registering the device
    service.registerUser(user);
  }
}
