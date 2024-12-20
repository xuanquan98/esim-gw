package com.quanvx.esim.repository;

import com.quanvx.esim.constant.enums.EnumStatusOrder;
import com.quanvx.esim.entity.AddressEntity;
import com.quanvx.esim.entity.EsimEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EsimRepository extends JpaRepository<EsimEntity, Long> {
    List<EsimEntity> findAllByOrderId(Long orderId);
    EsimEntity findFirstBySnPin(String snPin);
    List<EsimEntity> findAllByEnumStatusOrderAndTimeCheckQueryBefore(EnumStatusOrder enumStatusOrder, LocalDateTime timeCheckQuery);
}
