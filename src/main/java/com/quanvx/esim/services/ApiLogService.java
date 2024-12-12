package com.quanvx.esim.services;

public interface ApiLogService {
    void saveApiLog(String requestMethod, String requestUrl, String requestBody, int responseStatus, String responseBody);
}
