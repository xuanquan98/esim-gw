package com.quanvx.esim.request.joytel;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {
    private String customerCode;
    private int type;
    private String receiveName;
    private String phone;
    private long timestamp;
    private String autoGraph;
    private String remark;
    private String email;
    @JsonProperty("itemList")
    private List<Item> itemList;

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private String productCode;
        private int quantity;
    }
}
