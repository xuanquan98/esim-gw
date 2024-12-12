package com.quanvx.esim.services.impl;

import com.quanvx.esim.entity.ApiLog;
import com.quanvx.esim.repository.ApiLogRepository;
import com.quanvx.esim.services.ApiLogService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ApiLogServiceImpl implements ApiLogService {

    @Autowired
    private ApiLogRepository apiLogRepository;

    @Override
    public void saveApiLog(String requestMethod, String requestUrl, String requestBody, int responseStatus, String responseBody) {
        ApiLog apiLog = new ApiLog(requestMethod, requestUrl, requestBody, responseStatus, responseBody);
        //apiLogRepository.save(apiLog);
    }
}


