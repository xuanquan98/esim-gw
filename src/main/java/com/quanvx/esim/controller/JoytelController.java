package com.quanvx.esim.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;

@RestController
@Slf4j
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

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        // Gọi API từ URL đã nhận và trả về kết quả
        return ResponseEntity.ok().body("hello");
    }

    @GetMapping("/hello1")
    public ResponseEntity<?> hello1() {
        // Gọi API từ URL đã nhận và trả về kết quả
        return ResponseEntity.ok().body("hello");
    }
}
