package com.quanvx.esim.controller;

import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;
import com.quanvx.esim.services.SapoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class JoytelController {

    @Autowired
    private SapoService sapoService;

    // Inject RestTemplate để gửi HTTP request
    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/sapo/hook/orders/create")
    public ResponseEntity<?> hookOrderCreate(@RequestBody SapoOrderRequestDTO req) {
        sapoService.hookOrderCreate(req);
        return ResponseEntity.ok().body("/sapo/hook/orders/create");
    }
}
