package com.quanvx.esim.services;

import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.response.joytel.GenQrResponse;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderQueryResponse;
import com.quanvx.esim.response.joytel.OrderResponse;

public interface JoytelService {
    JoytelResponse<OrderResponse> orderJoytel(OrderRequestDTO req);

    JoytelResponse<OrderQueryResponse> orderJoytelQuery(OrderRequestDTO req);

    JoytelResponse<OrderResponse> genQrJoytel(OrderRequestDTO req, String transId);

    JoytelResponse<GenQrResponse> getQrJoytel(OrderRequestDTO req, String transId);
}
