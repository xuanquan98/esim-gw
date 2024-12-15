package com.quanvx.esim.response.joytel;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderQueryResponse {
    private String receiveName;
    private String phone;
    private String outboundCode;
    private String orderCode;
    private String remark;
    private List<Item> itemList;
    private String email;
    private int status;

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {

        private String productCode;
        private int quantity;
        private List<Sn> snList;
        private String productName;
    }

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Sn {

        private String snCode;
        private String productExpireDate;
        private String snPin;
    }
}
