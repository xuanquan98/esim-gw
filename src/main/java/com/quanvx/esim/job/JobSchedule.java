package com.quanvx.esim.job;

import com.quanvx.esim.config.AppConfig;
import com.quanvx.esim.constant.enums.EnumStatusOrder;
import com.quanvx.esim.entity.EsimEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import com.quanvx.esim.repository.EsimRepository;
import com.quanvx.esim.repository.SapoOrderRepository;
import com.quanvx.esim.request.joytel.OrderRequestDTO;
import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;
import com.quanvx.esim.response.joytel.JoytelResponse;
import com.quanvx.esim.response.joytel.OrderQueryResponse;
import com.quanvx.esim.response.joytel.OrderResponse;
import com.quanvx.esim.services.EncryptionService;
import com.quanvx.esim.services.JoytelService;
import com.quanvx.esim.services.impl.SapoServiceImpl;
import com.quanvx.esim.utils.DatetimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class JobSchedule {
    // Run every 3 minutes
    @Autowired
    private SapoOrderRepository orderRepository;
    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private JoytelService joytel;
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private EsimRepository esimRepository;

    private static final Logger log = LoggerFactory.getLogger(JobSchedule.class);

    @Scheduled(cron = "0 */1 * * * *") // Every 3 minutes
    public void runJobGetOrderQuery() {
        log.info("Job is running every 1 minutes: " + java.time.LocalDateTime.now());
        log.info("------- runJobGetOrderQuery ---------");
        //get order
        List<SapoOrderEntity> orderEntities = orderRepository.findAllByEnumStatusOrderAndTimeCheckQueryBefore(EnumStatusOrder.SEND_JOYTEL_SUCCESS, LocalDateTime.now().minusMinutes(1));
        // cac order
        log.info(orderEntities.toString());
        orderEntities.forEach(order -> {
            JoytelResponse<OrderQueryResponse> responeQuery = joytel.orderJoytelQuery(mockDataOrderQuery(order.getOrderTid(),order.getOrderCode()));
            log.info(responeQuery.toString());
            if(responeQuery.getCode() != 0 || responeQuery.getData() == null) {
                log.info("------- get order query false ---------");
                order.setEnumStatusOrder(EnumStatusOrder.GET_JOYTEL_QUERY_FAIL);
                orderRepository.save(order);
                log.info("------- end runJobGetOrderQuery ---------");
                return;
            }

            List<EsimEntity> esimEntities = esimRepository.findAllByOrderId(order.getDbId());
            // save snCode to entity
            responeQuery.getData().getItemList().forEach(res -> {
                List<EsimEntity> lstEsim = esimEntities.stream().filter(e -> Objects.equals(e.getProductCode(), res.getProductCode()) && e.getSnCode() == null).toList();
                IntStream.range(0, res.getSnList().size())
                        .forEach(index -> {
                            OrderQueryResponse.Sn data = res.getSnList().get(index);
                            if(data == null) return;
                            EsimEntity esim = lstEsim.get(index);
                            esim.setSnCode(data.getSnCode());
                            esim.setSnPin(data.getSnPin());
                            esim.setProductExpireDate(data.getProductExpireDate());
                            esimRepository.save(esim);
                        });

            });

            //
            List<String> snPin = responeQuery.getData().getItemList()
                    .stream()
                    .flatMap(item -> item.getSnList().stream())
                    .map(OrderQueryResponse.Sn::getSnPin)
                    .toList();

            log.info(snPin.toString());
            // genQR code
            snPin.forEach(e -> {
                OrderRequestDTO reqGenQr = new OrderRequestDTO();
                String transId = UUID.randomUUID().toString();
                reqGenQr.setCoupon(e);
                reqGenQr.setQrcodeType(1);
                JoytelResponse<OrderResponse> responseGenQR = joytel.genQrJoytel(reqGenQr, transId);
                EsimEntity esim = esimRepository.findFirstBySnPin(e);
                esim.setTransId(transId);
                esim.setTimeCheckQuery(LocalDateTime.now().plusMinutes(1));

                if(responseGenQR != null) {
                    esim.setEnumStatusOrder(EnumStatusOrder.SEND_JOYTEL_SUCCESS);
                } else {
                    esim.setEnumStatusOrder(EnumStatusOrder.SEND_JOYTEL_FAIL);
                }
                log.info(responseGenQR.toString());
                esimRepository.save(esim);
            });
        });

        log.info("------- end runJobGetOrderQuery ---------");
    }
    private OrderRequestDTO mockDataOrderQuery(String orderTid, String orderCode) {
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
