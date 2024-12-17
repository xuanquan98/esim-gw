package com.quanvx.esim.services.impl;

import com.quanvx.esim.config.AppConfig;
import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.response.joytel.GenQrResponse;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderQueryResponse;
import com.quanvx.esim.response.joytel.OrderResponse;
import com.quanvx.esim.services.JoytelService;
import com.quanvx.esim.utils.DatetimeUtil;
import com.quanvx.esim.utils.SHA1Encryption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
        log.info(String.valueOf(response.getBody()));
        return response.getBody();
        }


    @Override
    public JoytelResponse<OrderQueryResponse> orderJoytelQuery(OrderRequestDTO req) {
        HttpEntity<OrderRequestDTO> entity = new HttpEntity<>(req);
        ResponseEntity<JoytelResponse<OrderQueryResponse>> response =
                restTemplate.exchange(
                        String.format("%s%s", appConfig.getJoytelUrl(), appConfig.getJoytelUrlPathOrderQuery()),
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<JoytelResponse<OrderQueryResponse>>() {});
        log.info("---------");
        log.info(String.valueOf(response.getBody()));
        return response.getBody();
    }



    @Override
    public JoytelResponse<OrderResponse> genQrJoytel(OrderRequestDTO req, String transId) {
        HttpEntity<OrderRequestDTO> entity = new HttpEntity<>(req, createHeaders(transId));

        ResponseEntity<JoytelResponse<OrderResponse>> response =
                restTemplate.exchange(
                        String.format("%s%s", appConfig.getJoytelUrlV2(), appConfig.getJoytelPathGenQr()),
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<JoytelResponse<OrderResponse>>() {});
        log.info("---------");
        log.info(String.valueOf(response.getBody()));
        return response.getBody();
    }

    @Override
    public JoytelResponse<GenQrResponse> getQrJoytel(OrderRequestDTO req, String transId) {
        HttpEntity<OrderRequestDTO> entity = new HttpEntity<>(req, createHeaders(transId));

        ResponseEntity<JoytelResponse<GenQrResponse>> response =
                restTemplate.exchange(
                        String.format("%s%s", appConfig.getJoytelUrlV2(), appConfig.getJoytelPathGenQr()),
                        HttpMethod.POST,
                        entity,
                        new ParameterizedTypeReference<JoytelResponse<GenQrResponse>>() {});
        log.info("---------");
        log.info(String.valueOf(response.getBody()));
        return response.getBody();
    }

    public HttpHeaders createHeaders(String transId){
        // Generate the Timestamp (current time in milliseconds)
        long timestamp = DatetimeUtil.getTimestamp();

        // Create the Ciphertext
        String dataToEncrypt = appConfig.getJoytelAppId() + transId + timestamp + appConfig.getJoytelAppSecret();
        String ciphertext = SHA1Encryption.encryptWithMD5(dataToEncrypt);

        // Build the headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("AppId", appConfig.getJoytelAppId());
        headers.set("TransId", transId);
        headers.set("Timestamp", String.valueOf(timestamp));
        headers.set("Ciphertext", ciphertext);

        log.info(headers.toString());
        return headers;
    }

    }
