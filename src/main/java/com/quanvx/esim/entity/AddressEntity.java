package com.quanvx.esim.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address1;
    private String address2;
    private String city;
    private String firstName;
    private String lastName;
    private String phone;
    private String province;
    private int provinceCode;
    private String zip;
    private String countryCode;
    private String countryName;
    private String company;
    private String country;
    private String name;
    private String district;
    private int districtCode;
    private String ward;
    private int wardCode;
    private Double latitude;
    private Double longitude;
}
