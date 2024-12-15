package com.quanvx.esim.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "sapo_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SapoOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId; // Database-generated ID

    private Long id; // External server's ID

    private boolean buyerAcceptsMarketing;
    private String cancelReason;
    private String cancelledOn;
    private String cartToken;
    private String checkoutToken;
    private String closedOn;
    private String createdOn;
    private String currency;
    private String email;
    private String phone;
    private String customerGroupId;
    private String fulfillmentStatus;
    private String financialStatus;
    private String status;
    private String returnStatus;
    private String name;
    private String note;
    private int number;
    private int orderNumber;
    private String processedOn;
    private String processingMethod;
    private String sourceUrl;
    private String sourceName;
    private String source;
    private String landingSite;
    private String referringSite;
    private String reference;
    private String sourceIdentifier;
    private String gateway;
    private String token;
    private double totalDiscounts;
    private double totalLineItemsPrice;
    private double totalPrice;
    private double totalWeight;
    private String modifiedOn;
    private String tags;
    private String payAdjustmentStatus;
    private boolean test;

//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressEntity billingAddress;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressEntity shippingAddress;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private CustomerEntity customer;

//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "sapoOrder")
//    private List<LineItemEntity> lineItems;

////    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
////    private List<ShippingLineEntity> shippingLines;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private AppEntity app;
//
//    @ManyToOne(cascade = CascadeType.ALL)
//    private ChannelDefinitionEntity channelDefinition;

    private String userId;
    private String assigneeId;
    private int locationId;
    private boolean canMarkAsPaid;
    private boolean capturable;
    private boolean taxExempt;
    private boolean taxesIncluded;
    private double totalShippingPrice;
    private double totalTax;
    private double originalTotalPrice;
    private double cartDiscountAmount;
    private double netPayment;
    private double unpaidAmount;
    private double totalOutstanding;
    private double totalReceived;
    private double totalRefunded;
    private double totalRefundedShipping;
    private double currentCartDiscountAmount;
    private double currentTotalDiscounts;
    private double currentSubtotalPrice;
    private double currentTotalPrice;
    private double currentTotalTax;
    private double currentDiscountedTotal;
    private int printCount;
    private boolean canNotifyCustomer;
    private double currentTotalWeight;
    private boolean merchantEditable;
    private int subtotalLineItemsQuantity;
    @Column(name = "sub_total_price")
    private double subTotalPrice;
    @Column(name = "subtotal_price")
    private double subtotalPrice;

    @Column(name = "order_tid")
    private String orderTid;

    @Column(name = "order_code")
    private String orderCode;
}

