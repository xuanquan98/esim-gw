package com.quanvx.esim.controller;

import com.quanvx.esim.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

   @Autowired
   private AppConfig appConfig;
@Autowired
    private  RestTemplate restTemplate;

    @RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH})
    public ResponseEntity<?> proxyRequest(
            @RequestBody(required = false) String body,
            HttpMethod method,
            HttpServletRequest request) {

        // Build the target URL dynamically
        String requestURI = request.getRequestURI().substring("/proxy".length());
        String queryString = request.getQueryString();
        String url = appConfig.getJoytelUrl() + requestURI + (queryString != null ? "?" + queryString : "");

        // Forward headers
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.add(headerName, request.getHeader(headerName));
        }

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

