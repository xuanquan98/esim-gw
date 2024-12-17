package com.quanvx.esim.services.impl;

import com.quanvx.esim.config.AppConfig;
import com.quanvx.esim.constant.enums.EnumStatusOrder;
import com.quanvx.esim.entity.CustomerEntity;
import com.quanvx.esim.entity.EsimEntity;
import com.quanvx.esim.entity.LineItemEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import com.quanvx.esim.mapper.SapoOrderMapper;
import com.quanvx.esim.repository.CustomerRepository;
import com.quanvx.esim.repository.EsimRepository;
import com.quanvx.esim.repository.LineItemRepository;
import com.quanvx.esim.repository.SapoOrderRepository;
import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderQueryResponse;
import com.quanvx.esim.response.joytel.OrderResponse;
import com.quanvx.esim.services.EncryptionService;
import com.quanvx.esim.services.JoytelService;
import com.quanvx.esim.services.SapoService;
import com.quanvx.esim.utils.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class SapoServiceImpl implements SapoService {
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JoytelService joytel;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private SapoOrderRepository sapoOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private LineItemRepository lineItemRepository;
    @Autowired
    private EsimRepository esimRepository;
    private static final Logger log = LoggerFactory.getLogger(SapoServiceImpl.class);

    @Override
    public void hookOrderCreate(SapoOrderRequestDTO req) {
        log.info("------ start handle hookOrderCreate");
        log.info(req.toString());
        //save data to db
        // Map DTO to Entity
        SapoOrderEntity sapoOrder = SapoOrderMapper.INSTANCE.toEntity(req);
        sapoOrder.setEnumStatusOrder(EnumStatusOrder.INIT);
        sapoOrder = sapoOrderRepository.save(sapoOrder);
        // save customer

        SapoOrderRequestDTO.Customer customer = req.getCustomer();
        CustomerEntity customerEntity = SapoOrderMapper.INSTANCE.toEntity(customer);
        customerEntity.setOrderId(sapoOrder.getDbId());
        customerRepository.save(customerEntity);

        // save product
        List<SapoOrderRequestDTO.LineItem> lineItems = req.getLineItems();
        List<LineItemEntity> lineItemEntities = SapoOrderMapper.INSTANCE.mapLineItems(lineItems);
        SapoOrderEntity finalSapoOrder = sapoOrder;
        lineItemEntities.forEach(e -> e.setOrderId(finalSapoOrder.getDbId()));
        lineItemRepository.saveAll(lineItemEntities);

        //save esim
        List<EsimEntity> esimEntities = new ArrayList<>();
        SapoOrderEntity finalSapoOrder1 = sapoOrder;
        req.getLineItems().forEach(e -> {
            IntStream.range(0, 10).forEach(i -> {
                EsimEntity esim = new EsimEntity();
                esim.setOrderId(finalSapoOrder1.getDbId());
                esim.setEnumStatusOrder(EnumStatusOrder.INIT);
                esim.setProductCode("eSIM-test");
                esim.setProductName("eSIM-test");
                esim.setSapoName(e.getName());
                esimEntities.add(esim);
            });
        });
        esimRepository.saveAll(esimEntities);

        // mock data joytel
        JoytelResponse<OrderResponse> res = joytel.orderJoytel(mockDateJoytel(req));
        log.info(Optional.ofNullable(res).orElse(new JoytelResponse<>()).toString());
        if(res == null || res.getCode() != 0 || res.getData() == null) {
            sapoOrder.setEnumStatusOrder(EnumStatusOrder.SEND_JOYTEL_FAIL);
            sapoOrderRepository.save(sapoOrder);
            return;
        }
        sapoOrder.setOrderTid(res.getData().getOrderTid());
        sapoOrder.setOrderCode(res.getData().getOrderCode());
        sapoOrder.setTimeCheckQuery(LocalDateTime.now().plusMinutes(1));
        sapoOrder.setEnumStatusOrder(EnumStatusOrder.SEND_JOYTEL_SUCCESS);
        sapoOrderRepository.save(sapoOrder);
        log.info("------ end handle hookOrderCreate");
    }


    private OrderRequestDTO mockDateJoytel(SapoOrderRequestDTO req) {
        // Create the main DTO object
        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setCustomerCode(appConfig.getJoytelCustomerCode());
        orderRequest.setType(3);
        orderRequest.setReceiveName("test");
        orderRequest.setPhone("0838866309");
        orderRequest.setTimestamp(1667807404146L);
        orderRequest.setRemark("test");
        orderRequest.setEmail("quanvu143@gmail.com");

        // Create the item list
        List<OrderRequestDTO.Item> items = req.getLineItems().stream().map(e -> {
            OrderRequestDTO.Item item1 = new OrderRequestDTO.Item();
            item1.setProductCode("eSIM-test");
            item1.setQuantity(e.getQuantity());
            return  item1;
        }).toList();

        // Set the item list in the DTO
        orderRequest.setItemList(items);

        //gen autograp
        String customStr = getCustomString(orderRequest);
        String autoGraph = encryptionService.sha1Encrypt(customStr);
        orderRequest.setAutoGraph(autoGraph);
        return orderRequest;


    }


    private String getCustomString( OrderRequestDTO orderRequest) {
        // Concatenate itemList details
        String itemDetails = orderRequest.getItemList().stream()
                .map(item -> item.getProductCode() + item.getQuantity())
                .collect(Collectors.joining(""));

        // Construct final string
        return String.format("%s%s%s%s%s%s%s%s%s",
                appConfig.getJoytelCustomerCode(),
                appConfig.getJoytelCustomerAuth(),
                Optional.ofNullable(orderRequest.getWarehouse()).orElse(""),
                String.valueOf(orderRequest.getType()),
                Optional.ofNullable(orderRequest.getOrderTid()).orElse(""),
                Optional.ofNullable(orderRequest.getReceiveName()).orElse(""),
                Optional.ofNullable(orderRequest.getPhone()).orElse(""),
                String.valueOf(orderRequest.getTimestamp()),
                itemDetails);

    }

    private OrderRequestDTO mockDataOrderQuery(SapoOrderRequestDTO req, String orderTid, String orderCode) {
        OrderRequestDTO orderRequest = new OrderRequestDTO();
        orderRequest.setCustomerCode(appConfig.getJoytelCustomerCode());
        orderRequest.setOrderCode(orderCode);
        orderRequest.setTimestamp(DatetimeUtil.getTimestamp());
        orderRequest.setOrderTid(orderTid);
        String str = appConfig.getJoytelCustomerCode() + appConfig.getJoytelCustomerAuth() + Optional.ofNullable(orderCode).orElse("") + Optional.ofNullable(orderTid).orElse("") + String.valueOf(orderRequest.getTimestamp());
        String autoGraph = encryptionService.sha1Encrypt(str);
        orderRequest.setAutoGraph(autoGraph);

        return orderRequest;
    }



}
