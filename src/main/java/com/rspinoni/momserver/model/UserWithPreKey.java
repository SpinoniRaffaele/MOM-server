package com.rspinoni.momserver.model;

public record UserWithPreKey(String deviceId, String phoneNumber, String preKey) {
}
