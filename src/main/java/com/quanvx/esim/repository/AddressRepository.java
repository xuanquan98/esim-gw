package com.quanvx.esim.repository;

import com.quanvx.esim.entity.AddressEntity;
import com.quanvx.esim.entity.SapoOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {}
