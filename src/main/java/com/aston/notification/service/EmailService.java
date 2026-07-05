package com.aston.notification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            mailSender.send(message);
            log.info("✅ Email sent to {}", to);
        } catch (Exception e) {
            log.error("❌ Failed to send email to {}: {}", to, e.getMessage());
        }
    }

    public void sendCreationEmail(String email) {
        String text = "Здравствуйте! Ваш аккаунт на сайте был успешно создан.";
        sendEmail(email, "Регистрация на сайте", text);
    }

    public void sendDeletionEmail(String email) {
        String text = "Здравствуйте! Ваш аккаунт был удалён.";
        sendEmail(email, "Удаление аккаунта", text);
    }
}