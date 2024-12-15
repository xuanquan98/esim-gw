package com.quanvx.esim.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "apps")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;
    private Long id;

    private String key;
    private String alias;
}

