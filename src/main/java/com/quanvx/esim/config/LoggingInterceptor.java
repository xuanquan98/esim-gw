package com.quanvx.esim.config;

import com.quanvx.esim.services.ApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpRequest;

import org.springframework.http.client.ClientHttpResponse;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpRequestExecution;



import java.io.IOException;

@Component
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

    private final ApiLogService apiLogService;

    @Autowired
    public LoggingInterceptor(ApiLogService apiLogService) {
        this.apiLogService = apiLogService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        // Log the request
        String requestMethod = request.getMethod().toString();
        String requestUrl = request.getURI().toString();
        String requestBody = new String(body, "UTF-8");

        // Execute the request
        ClientHttpResponse response = execution.execute(request, body);

        // Log the response
        int responseStatus = response.getStatusCode().value();
        String responseBody = new String(response.getBody().readAllBytes(), "UTF-8");

        // Save the log to the database
        apiLogService.saveApiLog(requestMethod, requestUrl, requestBody, responseStatus, responseBody);

        return response;
    }
}

