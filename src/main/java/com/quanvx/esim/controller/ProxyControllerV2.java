package com.quanvx.esim.controller;

import com.quanvx.esim.config.AppConfig;
import com.quanvx.esim.services.impl.SapoServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Enumeration;

@RestController
@RequestMapping("/proxyV2")
public class ProxyControllerV2 {

   @Autowired
   private AppConfig appConfig;
    @Autowired
    private  RestTemplate restTemplate;
    private static final Logger log = LoggerFactory.getLogger(SapoServiceImpl.class);

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    public ResponseEntity<?> proxyRequest(
            @RequestBody(required = false) String body,
            HttpMethod method,
            HttpServletRequest request) {

        // Build the target URL dynamically
        String requestURI = request.getRequestURI().substring("/proxyV2".length());
        String queryString = request.getQueryString();
        String url = appConfig.getJoytelUrlV2() + requestURI + (queryString != null ? "?" + queryString : "");

        // Forward headers
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.add(headerName, request.getHeader(headerName));
        }
        log.info(headers.toString());
        // Create the request entity
        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

        // Forward the request
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, method, requestEntity, String.class);
            return new ResponseEntity<>(response.getBody(), response.getHeaders(), response.getStatusCode());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error forwarding request: " + e.getMessage());
        }
    }
}

