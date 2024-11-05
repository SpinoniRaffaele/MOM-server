package com.rspinoni.momserver.service;

import com.rspinoni.momserver.entities.UserEntity;
import com.rspinoni.momserver.model.User;
import com.rspinoni.momserver.model.UserWithPreKey;
import com.rspinoni.momserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizationService {

  private final UserRepository repository;

  @Autowired
  AuthorizationService(UserRepository repository) {
    this.repository = repository;
  }

  public boolean validateUser(User user) {
    Optional<UserEntity> userOpt = this.repository.findById(user.phoneNumber());
    return userOpt.isPresent() && userOpt.get().getDeviceId().equals(user.deviceId());
  }

  public void registerUser(UserWithPreKey user) {
    Optional<UserEntity> userEntityOpt = this.repository.findById(user.phoneNumber());
    if (userEntityOpt.isPresent()) {
      UserEntity userEntity = userEntityOpt.get();
      userEntity.setDeviceId(user.deviceId());
      userEntity.setPreKey(user.preKey());
      this.repository.save(userEntity);
    } else {
      this.repository.save(new UserEntity(user.deviceId(), user.phoneNumber(), user.preKey()));
    }
  }
}
