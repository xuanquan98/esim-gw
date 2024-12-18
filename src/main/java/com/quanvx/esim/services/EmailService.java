package com.quanvx.esim.services;

import com.quanvx.esim.entity.EsimEntity;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

import java.util.Map;

public interface EmailService {
    void sendEmail(String to, String subject, String template, Map<String, Object> variables) throws MessagingException;

    void sendMailQr(EsimEntity model);
}
