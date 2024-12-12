package com.quanvx.esim.services.impl;

import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderResponse;
import com.quanvx.esim.services.JoytelService;
import com.quanvx.esim.services.SapoService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class SapoServiceImpl implements SapoService {
    @Autowired
    private JoytelService joytel;
    private static final Logger log = LoggerFactory.getLogger(SapoServiceImpl.class);

    @Override
    public void hookOrderCreate(SapoOrderRequestDTO req) {
        log.info("------ start handle hookOrderCreate");
        log.info(req.toString());

        // mock data joytel
        JoytelResponse<OrderResponse> res = joytel.orderJoytel(mockDateJoytel());
        log.info(Optional.ofNullable(res).orElse(new JoytelResponse<>()).toString());


        log.info("------ end handle hookOrderCreate");
    }


    private OrderRequestDTO mockDateJoytel() {
        // Create the main DTO object
        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setCustomerCode("test001");
        orderRequest.setType(3);
        orderRequest.setReceiveName("test");
        orderRequest.setPhone("15666666666");
        orderRequest.setTimestamp(1667807404146L);
        orderRequest.setAutoGraph("ae09d951095d44faabf3c91a9879afdc477dd630");
        orderRequest.setRemark("test");
        orderRequest.setEmail("test@qq.com");

        // Create the item list
        List<OrderRequestDTO.Item> items = new ArrayList<>();
        OrderRequestDTO.Item item1 = new OrderRequestDTO.Item();
        item1.setProductCode("esim615xxxx1");
        item1.setQuantity(1);

        OrderRequestDTO.Item item2 = new OrderRequestDTO.Item();
        item2.setProductCode("esim615xxxx2");
        item2.setQuantity(1);

        // Add items to the list
        items.add(item1);
        items.add(item2);

        // Set the item list in the DTO
        orderRequest.setItemList(items);
        return orderRequest;
    }

}
