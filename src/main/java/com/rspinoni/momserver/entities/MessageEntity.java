package com.rspinoni.momserver.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class MessageEntity {

  @Id
  private String id;

  private String sendersPhoneNumber;

  private String receiversPhoneNumber;

  private String content;
}
