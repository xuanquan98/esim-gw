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
    private String salePlanDays;
}
