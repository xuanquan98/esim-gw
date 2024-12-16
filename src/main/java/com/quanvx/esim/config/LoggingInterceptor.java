package com.quanvx.esim.config;

import com.quanvx.esim.services.ApiLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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
        String requestBody = new String(body, StandardCharsets.UTF_8);

        // Execute the request and buffer the response
        ClientHttpResponse response = execution.execute(request, body);
        ClientHttpResponse bufferedResponse = bufferResponse(response);

        // Log the response
        int responseStatus = bufferedResponse.getStatusCode().value();
        //String responseBody = new String(bufferedResponse.getBody().readAllBytes(), StandardCharsets.UTF_8);
        String responseBody = new String(
                new String(bufferedResponse.getBody().readAllBytes(), StandardCharsets.ISO_8859_1)
                        .replace("\u0000", "") // Remove null bytes
                        .getBytes(StandardCharsets.ISO_8859_1),
                StandardCharsets.UTF_8
        );

        // Save the log to the database
        apiLogService.saveApiLog(requestMethod, requestUrl, requestBody, responseStatus, responseBody);

        return bufferedResponse;
    }

    private ClientHttpResponse bufferResponse(ClientHttpResponse response) throws IOException {
        // Read the response body and buffer it
        byte[] responseBody = response.getBody().readAllBytes();

        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return (HttpStatus) response.getStatusCode();
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return response.getRawStatusCode();
            }

            @Override
            public String getStatusText() throws IOException {
                return response.getStatusText();
            }

            @Override
            public void close() {
                response.close();
            }

            @Override
            public InputStream getBody() {
                return new ByteArrayInputStream(responseBody);
            }

            @Override
            public HttpHeaders getHeaders() {
                return response.getHeaders();
            }
        };
    }
}
