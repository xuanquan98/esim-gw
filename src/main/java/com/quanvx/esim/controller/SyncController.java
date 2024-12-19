package com.quanvx.esim.controller;

import com.quanvx.esim.entity.CodeMappingEntity;
import com.quanvx.esim.repository.CodeMappingRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SyncController {
    @Autowired
    private CodeMappingRepository codeMappingRepository;

    @PostMapping("/sync/code_mapping")
    public ResponseEntity<String> syncCodeMapping(@RequestBody CodeMappingEntity req) {
        codeMappingRepository.deleteAll();
        codeMappingRepository.save(req);
        return ResponseEntity.ok().body("/sync/code_mapping");

    }
}
