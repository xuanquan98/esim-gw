package com.quanvx.esim.controller;

import com.quanvx.esim.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("name", "Người dùng");

        try {
            emailService.sendEmail("vuquan418@gmail.com", "Chào mừng!", "email-template", variables);
            return "Email đã được gửi!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Lỗi khi gửi email!";
        }
    }
}
