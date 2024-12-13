package com.quanvx.esim.services.impl;

import com.quanvx.esim.services.EncryptionService;
import com.quanvx.esim.utils.SHA1Encryption;
import org.springframework.stereotype.Service;

@Service
public class EncryptionServiceImpl implements EncryptionService {
    @Override
    public String sha1Encrypt(String input) {
        return SHA1Encryption.encrypt(input);
    }
}
