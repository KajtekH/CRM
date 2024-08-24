package com.kajtekh.notificationservice.kafka;

import com.kajtekh.notificationservice.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    @KafkaListener(topics = "user-registration", groupId = "notification-group")
    public void listen(String message) {
        notificationService.sendEmail(message, "Welcome!", "Welcome to our service!");
    }
}
