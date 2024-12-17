package com.quanvx.esim.config;

import jakarta.validation.Valid;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Data
public class AppConfig {
    @Value("${joytel.url}")
    private String joytelUrl;
    @Value("${joytel.url.path-order}")
    private String joytelUrlPathOrder;
    @Value("${joytel.url.path-order.query}")
    private String joytelUrlPathOrderQuery;
    @Value("${joytel.customer-code}")
    private String joytelCustomerCode;
    @Value("${joytel.customer-auth}")
    private String joytelCustomerAuth;
    @Value("${joytel.urlv2}")
    private String joytelUrlV2;
    @Value("${joytel.app-id}")
    private String joytelAppId;
    @Value("${joytel.app-secret}")
    private String joytelAppSecret;
    @Value("${joytel.urlv2.path-gen-qr}")
    private String joytelPathGenQr;
    @Value("${joytel.urlv2.path-get-qr}")
    private String getJoytelPathGetQr;

}
