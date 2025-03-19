package com.rspinoni.momserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rspinoni.momserver.model.Message;
import com.rspinoni.momserver.repository.MessageRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MessageService {

  private final MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public List<Message> retrieveQueuedMessages(String phoneNumber) {
    return this.messageRepository.findAllByReceiversPhoneNumber(phoneNumber).stream()
        .map(messageEntity -> new Message(
            messageEntity.getId(),
            messageEntity.getSendersPhoneNumber(),
            messageEntity.getReceiversPhoneNumber(),
            messageEntity.getContent()
        ))
        .toList();
  }

  public void deleteMessagesFromSender(String senderPhoneNumber) {
    this.messageRepository.deleteBySendersPhoneNumber(senderPhoneNumber);
  }
}
