package com.quanvx.esim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_logs")
@AllArgsConstructor
@Data
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_method", length = 50) // Specify length for request method
    private String requestMethod;

    @Column(name = "request_url", length = 512) // Custom length for URL
    private String requestUrl;

    @Column(name = "request_body", columnDefinition = "TEXT") // Use TEXT for larger content
    private String requestBody;

    @Column(name = "response_status")
    private int responseStatus;

    @Column(name = "response_body", columnDefinition = "TEXT") // Use TEXT for larger content
    private String responseBody;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public ApiLog() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiLog(String requestMethod, String requestUrl, String requestBody, int responseStatus, String responseBody) {
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestBody = requestBody;
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
        this.timestamp = LocalDateTime.now();
    }
}
