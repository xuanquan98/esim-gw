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

}
