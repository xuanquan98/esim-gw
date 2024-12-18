package com.quanvx.esim.services.impl;

import com.quanvx.esim.entity.EsimEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import com.quanvx.esim.repository.SapoOrderRepository;
import com.quanvx.esim.services.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.ui.Model;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private SapoOrderRepository sapoOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    @Async
    public void sendEmail(String to, String subject, String template, Map<String, Object> variables) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        // Tạo nội dung email từ Thymeleaf template
        Context context = new Context();
        context.setVariables(variables);
        String htmlContent = templateEngine.process(template, context);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }


    @Override
    public void sendMailQr(EsimEntity esim){
        Optional<SapoOrderEntity> order = sapoOrderRepository.findById(esim.getOrderId());
        String subject = "eSIM" + order.get().getOrderCode();
        String mailTo = order.get().getEmail();
            Map<String, Object> variables = new HashMap<>();
            variables.put("orderTime", LocalDateTime.now().toString());
            variables.put("sn", esim.getSnCode());
            variables.put("pin", esim.getPin1());
            variables.put("puk", esim.getPuk1());
            variables.put("activationCode", "Người dùng");
            variables.put("qrCodeImage", esim.getQrcode());
            variables.put("orderID", order.get().getOrderCode());
        try {
            sendEmail(mailTo, subject, "email-template", variables);
            log.info("Email đã được gửi!");
        } catch (MessagingException e) {
            e.printStackTrace();
            log.info("Lỗi khi gửi email!");
        }


    }
}
