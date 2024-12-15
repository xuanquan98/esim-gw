package com.quanvx.esim.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId; // Database-generated ID

    private Long id; // External server's ID

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
    private double totalSpent;
    private int ordersCount;
    private boolean acceptsMarketing;
    private boolean verifiedEmail;

//    @OneToOne(cascade = CascadeType.ALL)
//    private AddressEntity defaultAddress;

    private String createdOn;
    private String modifiedOn;
    private long orderId;
}
