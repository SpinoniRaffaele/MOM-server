package com.rspinoni.momserver.repository;

import com.rspinoni.momserver.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, String> {
}
