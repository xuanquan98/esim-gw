package com.quanvx.esim.repository;

import com.quanvx.esim.entity.LineItemEntity;
import com.quanvx.esim.entity.ShippingLineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingLineRepository extends JpaRepository<ShippingLineEntity, Long> {}
