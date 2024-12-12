package com.quanvx.esim.services;

import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderResponse;

public interface JoytelService {
    JoytelResponse<OrderResponse> orderJoytel(OrderRequestDTO req);
}
