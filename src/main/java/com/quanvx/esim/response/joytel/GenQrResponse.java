package com.quanvx.esim.response.joytel;

import lombok.*;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenQrResponse {
    private String coupon;
    private Integer qrcodeType;
    private String qrcode;
    private String salePlanName;
    private Integer salePlanDays;
    private String cid;
    private String pin1;
    private String pin2;
    private String puk1;
    private String puk2;
}
