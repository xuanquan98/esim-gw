package com.quanvx.esim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/sapo/hook/orders/create")
    public ResponseEntity<?> hookOrderCreate(@RequestBody Object obj) {

        // Log the request body
        System.out.println("Request Body: " + obj.toString());
        // Gọi API từ URL đã nhận và trả về kết quả
        return ResponseEntity.ok().body("/sapo/hook/orders/create");
    }
}
