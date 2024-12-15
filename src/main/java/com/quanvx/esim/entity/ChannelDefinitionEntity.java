package com.quanvx.esim.entity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "channel_definitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChannelDefinitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dbId;

    private Long id;

    private String mainName;
    private String subName;
    private String alias;
}

