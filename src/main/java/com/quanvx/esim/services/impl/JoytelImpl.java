package com.quanvx.esim.services.impl;

import com.quanvx.esim.config.AppConfig;
import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderResponse;
import com.quanvx.esim.services.JoytelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JoytelImpl implements JoytelService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppConfig appConfig;

    private static final Logger log = LoggerFactory.getLogger(JoytelImpl.class);

    @Override
    public JoytelResponse<OrderResponse> orderJoytel(OrderRequestDTO req) {
            HttpEntity<OrderRequestDTO> entity = new HttpEntity<>(req);
            ResponseEntity<JoytelResponse<OrderResponse>> response =
                    restTemplate.exchange(
                            String.format("%s%s", appConfig.getJoytelUrl(), appConfig.getJoytelUrlPathOrder()),
                            HttpMethod.POST,
                            entity,
                            new ParameterizedTypeReference<JoytelResponse<OrderResponse>>() {});
        log.info("---------");
        log.info(String.valueOf(response));
        return response.getBody();
        }
    }
