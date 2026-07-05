package com.aston.notification.listener;

import com.aston.notification.dto.UserEventDto;
import com.aston.notification.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserEventListener {

    private static final Logger log = LoggerFactory.getLogger(UserEventListener.class);
    private final EmailService emailService;

    public UserEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events-2", groupId = "notification-group")
    public void handleUserEvent(UserEventDto event) {
        log.info("📩 Received event: operation={}, email={}", event.getOperation(), event.getEmail());

        try {
            if ("CREATED".equals(event.getOperation())) {
                emailService.sendCreationEmail(event.getEmail());
            } else if ("DELETED".equals(event.getOperation())) {
                emailService.sendDeletionEmail(event.getEmail());
            } else {
                log.warn("Unknown operation: {}", event.getOperation());
            }
        } catch (Exception e) {
            log.error("Error processing event: {}", e.getMessage());
        }
    }
}