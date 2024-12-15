package com.quanvx.esim.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "line_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LineItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    private Long id;

    private double price;
    private double totalDiscount;
    private String discountCode;
    private String fulfillmentStatus;
    private int fulfillableQuantity;
    private int quantity;
    private int currentQuantity;
    private int grams;
    private Long productId;
    private Long variantId;
    private Long inventoryItemId;
    private boolean productExists;
    private String variantInventoryManagement;
    private boolean requiresShipping;
    private String name;
    private String title;
    private String variantTitle;
    private String sku;
    private String vendor;
    private boolean giftCard;
    private long orderId;

//    @ManyToOne
//    //@JoinColumn(name = "dbId")
//    private SapoOrderEntity sapoOrder;
}

