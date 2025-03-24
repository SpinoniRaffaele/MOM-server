package com.rspinoni.momserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rspinoni.momserver.entities.UserEntity;
import com.rspinoni.momserver.model.User;
import com.rspinoni.momserver.model.exception.UnexistentUserException;
import com.rspinoni.momserver.repository.UserRepository;

@Service
public class UserService {

  private final UserRepository repository;

  @Autowired
  UserService(UserRepository repository) {
    this.repository = repository;
  }

  public boolean validateUser(User user) {
    Optional<UserEntity> userOpt = this.repository.findById(user.phoneNumber());
    return userOpt.isPresent() && userOpt.get().getDeviceId().equals(user.deviceId());
  }

  public void registerUser(User user) {
    Optional<UserEntity> userEntityOpt = this.repository.findById(user.phoneNumber());
    if (userEntityOpt.isPresent()) {
      UserEntity userEntity = userEntityOpt.get();
      userEntity.setDeviceId(user.deviceId());
      this.repository.save(userEntity);
    } else {
      this.repository.save(new UserEntity(user.deviceId(), user.phoneNumber(), null));
    }
  }

  public String getPreKey(String phoneNumber) {
    Optional<UserEntity> userEntityOpt = this.repository.findById(phoneNumber);
    return userEntityOpt.map(UserEntity::getPreKey)
        .orElseThrow(() -> new UnexistentUserException("User not found"));
  }
}
