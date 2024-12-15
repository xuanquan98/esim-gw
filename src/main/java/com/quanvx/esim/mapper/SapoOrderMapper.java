package com.quanvx.esim.mapper;

import com.quanvx.esim.entity.*;
import com.quanvx.esim.request.sapo.SapoOrderRequestDTO;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SapoOrderMapper {

    SapoOrderMapper INSTANCE = Mappers.getMapper(SapoOrderMapper.class);

    // Map SapoOrderRequestDTO to SapoOrder
   
    SapoOrderEntity toEntity(SapoOrderRequestDTO dto);

    // Map Address DTO to Entity
   
    AddressEntity toEntity(SapoOrderRequestDTO.Address dto);

    // Map Customer DTO to Entity
   
    CustomerEntity toEntity(SapoOrderRequestDTO.Customer dto);

    // Map LineItem DTO to Entity
   
    LineItemEntity toEntity(SapoOrderRequestDTO.LineItem dto);

    // Map ShippingLine DTO to Entity
   
    ShippingLineEntity toEntity(SapoOrderRequestDTO.ShippingLine dto);

    // Map App DTO to Entity
   
    AppEntity toEntity(SapoOrderRequestDTO.App dto);

    // Map ChannelDefinition DTO to Entity
   
    ChannelDefinitionEntity toEntity(SapoOrderRequestDTO.ChannelDefinition dto);

    // List mappings for collections
    List<LineItemEntity> mapLineItems(List<SapoOrderRequestDTO.LineItem> lineItems);
    List<ShippingLineEntity> mapShippingLines(List<SapoOrderRequestDTO.ShippingLine> shippingLines);
}

