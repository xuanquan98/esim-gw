package com.quanvx.esim.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "shipping_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShippingLineEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    private Long id;

    private String title;
    private String code;
    private String source;
    private double price;

//    @ManyToOne
//    private SapoOrderEntity order;
}

