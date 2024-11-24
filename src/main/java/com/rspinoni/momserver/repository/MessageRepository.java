package com.rspinoni.momserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rspinoni.momserver.entities.MessageEntity;
import com.rspinoni.momserver.entities.UserEntity;

public interface MessageRepository extends CrudRepository<UserEntity, String> {

  @Query("FROM MessageEntity m WHERE m.receiversPhoneNumber = :phoneNumber")
  List<MessageEntity> findAllByReceiversPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
