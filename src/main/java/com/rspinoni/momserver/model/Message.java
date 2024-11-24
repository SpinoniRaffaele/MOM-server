package com.rspinoni.momserver.model;

public record Message(String id, String sendersPhoneNumber, String receiversPhoneNumber, String content) {
}
