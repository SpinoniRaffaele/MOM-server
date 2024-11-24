package com.rspinoni.momserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rspinoni.momserver.model.PreKeyContainer;
import com.rspinoni.momserver.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("{phoneNumber}/preKey")
  public PreKeyContainer getPreKeyFromPhoneNumber(@PathVariable String phoneNumber) {
    return new PreKeyContainer(userService.getPreKey(phoneNumber));
  }
}
