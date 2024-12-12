package com.quanvx.esim.request.sapo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nonnull;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SapoOrderRequestDTO {

    private Long id;
    @JsonProperty("buyer_accepts_marketing")
    private boolean buyerAcceptsMarketing;
    private String cancelReason;
    private String cancelledOn;
    @JsonProperty("cart_token")
    private String cartToken;
    @JsonProperty("checkout_token")
    private String checkoutToken;
    private String closedOn;
    @JsonProperty("created_on")
    private String createdOn;
    private String currency;
    private String email;
    private String phone;
    @JsonProperty("customer_group_id")
    private String customerGroupId;
    @JsonProperty("fulfillment_status")
    private String fulfillmentStatus;
    @JsonProperty("financial_status")
    private String financialStatus;
    private String status;
    @JsonProperty("return_status")
    private String returnStatus;
    private String name;
    private String note;
    private int number;
    @JsonProperty("order_number")
    private int orderNumber;
    @JsonProperty("processed_on")
    private String processedOn;
    @JsonProperty("processing_method")
    private String processingMethod;
    private String sourceUrl;
    private String sourceName;
    private String source;
    @JsonProperty("landing_site")
    private String landingSite;
    @JsonProperty("referring_site")
    private String referringSite;
    private String reference;
    @JsonProperty("source_identifier")
    private String sourceIdentifier;
    private String gateway;
    private String token;
    @JsonProperty("total_discounts")
    private double totalDiscounts;
    @JsonProperty("total_line_items_price")
    private double totalLineItemsPrice;
    @JsonProperty("total_price")
    private double totalPrice;
    @JsonProperty("total_weight")
    private double totalWeight;
    @JsonProperty("modified_on")
    private String modifiedOn;
    private String tags;
    @JsonProperty("pay_adjustment_status")
    private String payAdjustmentStatus;
    private boolean test;
    @JsonProperty("billing_address")
    private Address billingAddress;
    @JsonProperty("shipping_address")
    private Address shippingAddress;
    private Customer customer;
    private List<LineItem> lineItems;
    @JsonProperty("shipping_lines")
    private List<ShippingLine> shippingLines;
    private List<Object> fulfillments;
    private List<Object> refunds;
    @JsonProperty("note_attributes")
    private List<Object> noteAttributes;
    @JsonProperty("discount_codes")
    private List<Object> discountCodes;
    @JsonProperty("discount_applications")
    private List<Object> discountApplications;
    @JsonProperty("combination_lines")
    private List<Object> combinationLines;
    private App app;
    private ChannelDefinition channelDefinition;
    private String userId;
    private String assigneeId;
    @JsonProperty("location_id")
    private int locationId;
    @JsonProperty("payment_gateway_names")
    private List<String> paymentGatewayNames;
    @JsonProperty("can_mark_as_paid")
    private boolean canMarkAsPaid;
    private boolean capturable;
    @JsonProperty("tax_exempt")
    private boolean taxExempt;
    @JsonProperty("taxes_included")
    private boolean taxesIncluded;
    private List<Object> taxLines;
    @JsonProperty("total_shipping_price")
    private double totalShippingPrice;
    @JsonProperty("total_tax")
    private double totalTax;
    @JsonProperty("original_total_price")
    private double originalTotalPrice;
    @JsonProperty("cart_discount_amount")
    private double cartDiscountAmount;
    @JsonProperty("net_payment")
    private double netPayment;
    @JsonProperty("unpaid_amount")
    private double unpaidAmount;
    @JsonProperty("total_outstanding")
    private double totalOutstanding;
    @JsonProperty("total_received")
    private double totalReceived;
    @JsonProperty("total_refunded")
    private double totalRefunded;
    @JsonProperty("total_refunded_shipping")
    private double totalRefundedShipping;
    @JsonProperty("current_cart_discount_amount")
    private double currentCartDiscountAmount;
    @JsonProperty("current_total_discounts")
    private double currentTotalDiscounts;
    @JsonProperty("current_subtotal_price")
    private double currentSubtotalPrice;
    @JsonProperty("current_total_price")
    private double currentTotalPrice;
    @JsonProperty("current_total_tax")
    private double currentTotalTax;
    @JsonProperty("current_discounted_total")
    private double currentDiscountedTotal;
    @JsonProperty("current_tax_lines")
    private List<Object> currentTaxLines;
    @JsonProperty("print_count")
    private int printCount;
    @JsonProperty("can_notify_customer")
    private boolean canNotifyCustomer;
    @JsonProperty("current_total_weight")
    private double currentTotalWeight;
    @JsonProperty("merchant_editable")
    private boolean merchantEditable;
    @JsonProperty("subtotal_line_items_quantity")
    private int subtotalLineItemsQuantity;
    @JsonProperty("sub_total_price")
    private double subTotalPrice;
    @JsonProperty("subtotal_price")
    private double subtotalPrice;

    // Getters and Setters for all fields

    // Address class
    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String address1;
        private String address2;
        private String city;
        private String firstName;
        private String lastName;
        private String phone;
        private String province;
        @JsonProperty("province_code")
        private int provinceCode;
        private String zip;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("country_name")
        private String countryName;
        private String company;
        private String country;
        private String name;
        private String district;
        @JsonProperty("district_code")
        private int districtCode;
        private String ward;
        @JsonProperty("ward_code")
        private int wardCode;
        private Double latitude;
        private Double longitude;

        // Getters and Setters for Address fields
    }

    // Customer class

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Customer {
        private Long id;
        private String state;
        private String email;
        private String phone;
        private String firstName;
        private String lastName;
        private String dob;
        private String note;
        private String gender;
        private String lastOrderName;
        private Long lastOrderId;
        @JsonProperty("total_spent")
        private double totalSpent;
        @JsonProperty("orders_count")
        private int ordersCount;
        @JsonProperty("accepts_marketing")
        private boolean acceptsMarketing;
        @JsonProperty("verified_email")
        private boolean verifiedEmail;
        @JsonProperty("default_address")
        private Address defaultAddress;
        @JsonProperty("created_on")
        private String createdOn;
        @JsonProperty("modified_on")
        private String modifiedOn;

        // Getters and Setters for Customer fields
    }

    // LineItem class

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LineItem {
        private Long id;
        private double price;
        @JsonProperty("total_discount")
        private double totalDiscount;
        @JsonProperty("discount_code")
        private String discountCode;
        @JsonProperty("fulfillment_status")
        private String fulfillmentStatus;
        @JsonProperty("fulfillable_quantity")
        private int fulfillableQuantity;
        private int quantity;
        @JsonProperty("current_quantity")
        private int currentQuantity;
        private int grams;
        @JsonProperty("product_id")
        private Long productId;
        @JsonProperty("variant_id")
        private Long variantId;
        @JsonProperty("inventory_item_id")
        private Long inventoryItemId;
        private boolean productExists;
        @JsonProperty("variant_inventory_management")
        private String variantInventoryManagement;
        private boolean requiresShipping;
        private String name;
        private String title;
        @JsonProperty("variant_title")
        private String variantTitle;
        private String sku;
        private String vendor;
        @JsonProperty("gift_card")
        private boolean giftCard;
        private List<Object> properties;
        private List<Object> discountAllocations;
        private List<Object> taxLines;
        private boolean taxable;
        @JsonProperty("non_fulfillable_quantity")
        private int nonFulfillableQuantity;
        @JsonProperty("refundable_quantity")
        private int refundableQuantity;
        private boolean restockable;
        @JsonProperty("discounted_unit_price")
        private double discountedUnitPrice;
        @JsonProperty("discounted_total")
        private double discountedTotal;
        @JsonProperty("original_total")
        private double originalTotal;
        @JsonProperty("fulfillment_service")
        private String fulfillmentService;
        @JsonProperty("merchant_editable")
        private boolean merchantEditable;

        // Getters and Setters for LineItem fields
    }

    // ShippingLine class

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShippingLine {
        private String title;
        private String code;
        private String source;
        private double price;
        @JsonProperty("discount_allocations")
        private List<Object> discountAllocations;
        @JsonProperty("tax_lines")
        private List<Object> taxLines;

        // Getters and Setters for ShippingLine fields
    }

    // App class

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class App {
        private Long id;
        private String key;
        private String alias;

        // Getters and Setters for App fields
    }

    // ChannelDefinition class

    @Setter
    @Getter
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ChannelDefinition {
        private Long id;
        private String mainName;
        private String subName;
        private String alias;

        // Getters and Setters for ChannelDefinition fields
    }

    // Getters and Setters for all the main DTO class fields
}
