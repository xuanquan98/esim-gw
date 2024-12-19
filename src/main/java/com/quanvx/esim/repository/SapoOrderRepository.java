package com.quanvx.esim.repository;

import com.quanvx.esim.constant.enums.EnumStatusOrder;
import com.quanvx.esim.entity.SapoOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SapoOrderRepository extends JpaRepository<SapoOrderEntity, Long> {
    List<SapoOrderEntity> findAllByEnumStatusOrderAndTimeCheckQueryBefore(EnumStatusOrder enumStatusOrder, LocalDateTime timecheckQuery);

    List<SapoOrderEntity> findAllByEnumStatusOrder(EnumStatusOrder enumStatusOrder);
}
