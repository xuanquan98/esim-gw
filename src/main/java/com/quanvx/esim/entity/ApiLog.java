package com.quanvx.esim.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_logs")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_method")
    private String requestMethod;

    @Column(name = "request_url")
    private String requestUrl;

    @Column(name = "request_body")
    private String requestBody;

    @Column(name = "response_status")
    private int responseStatus;

    @Column(name = "response_body")
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