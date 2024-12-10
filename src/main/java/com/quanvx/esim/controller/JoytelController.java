package com.quanvx.esim.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class JoytelController {

    // Inject RestTemplate để gửi HTTP request
    @Autowired
    private RestTemplate restTemplate;

    // API endpoint của Gateway
    @GetMapping("/gateway")
    public String gateway(@RequestParam String url) {
        // Gọi API từ URL đã nhận và trả về kết quả
        return restTemplate.getForObject(url, String.class);
    }
}
