package com.quanvx.esim.services;

import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;

public interface SapoService {
    void hookOrderCreate(SapoOrderRequestDTO req);
}
