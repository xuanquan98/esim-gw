package com.quanvx.esim.repository;

import com.quanvx.esim.entity.CustomerEntity;
import com.quanvx.esim.entity.LineItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineItemRepository extends JpaRepository<LineItemEntity, Long> {}
