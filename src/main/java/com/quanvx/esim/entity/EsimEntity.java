package com.quanvx.esim.entity;

import com.quanvx.esim.constant.enums.EnumStatusOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "esim")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EsimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;

    private String productCode;
    private String productName;
    private String sapoName;
    private String sapoCode;

    private String snCode;
    private String snPin;
    private String status;
    private String productExpireDate;
    private String statusDesc;

    private LocalDateTime timeCheckQuery;
    @Enumerated(EnumType.STRING)
    private EnumStatusOrder enumStatusOrder;
    private String transId;
    private String coupon;
    private Integer qrcodeType;
    private String qrcode;
    private String cid;
    private String salePlanName;
    private Integer salePlanDays;
    private String pin1;
    private String pin2;
    private String puk1;
    private String puk2;

}

