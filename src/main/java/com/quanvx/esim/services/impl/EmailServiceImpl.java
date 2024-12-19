package com.quanvx.esim.services.impl;

import com.quanvx.esim.constant.enums.EnumStatusOrder;
import com.quanvx.esim.entity.EsimEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import com.quanvx.esim.repository.EsimRepository;
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

import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private SapoOrderRepository sapoOrderRepository;

    private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);
    @Autowired
    private EsimRepository esimRepository;

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
    public void sendMailQr(SapoOrderEntity order) {
        List<EsimEntity> esims = esimRepository.findAllByOrderId(order.getDbId());
        String subject = "eSIM" + order.getOrderCode();
        String mailTo = order.getEmail();
        Map<String, Object> variables = new HashMap<>();
        variables.put("orders", esims);
        variables.put("orderID", order.getOrderCode());
        try {
            sendEmail(mailTo, subject, "email-template", variables);
            order.setEnumStatusOrder(EnumStatusOrder.SEND_MAIL_SUCCESS);
            sapoOrderRepository.save(order);
            log.info("Email đã được gửi!");
        } catch (MessagingException e) {
            order.setEnumStatusOrder(EnumStatusOrder.SEND_MAIL_FAIL);
            sapoOrderRepository.save(order);
            e.printStackTrace();
            log.info("Lỗi khi gửi email!");
        }
    }
}
