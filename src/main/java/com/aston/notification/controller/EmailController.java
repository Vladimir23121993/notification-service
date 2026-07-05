package com.aston.notification.controller;

import com.aston.notification.dto.UserEventDto;
import com.aston.notification.service.EmailService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody UserEventDto event) {
        if ("CREATED".equals(event.getOperation())) {
            emailService.sendCreationEmail(event.getEmail());
            return "✅ Creation email sent to " + event.getEmail();
        } else if ("DELETED".equals(event.getOperation())) {
            emailService.sendDeletionEmail(event.getEmail());
            return "✅ Deletion email sent to " + event.getEmail();
        } else {
            return "❌ Unknown operation: " + event.getOperation();
        }
    }
}